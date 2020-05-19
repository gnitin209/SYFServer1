package com.mindtree.sellyourfurniture.service.impl;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.DisplayReviewDTO;
import com.mindtree.sellyourfurniture.dto.PostFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.ViewProductDTO;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.FurnitureImage;
import com.mindtree.sellyourfurniture.entity.OrderItem;
import com.mindtree.sellyourfurniture.entity.Review;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidFurnitureTypeException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidPriceException;
import com.mindtree.sellyourfurniture.exception.service.custom.NoResultsFurnitureException;
import com.mindtree.sellyourfurniture.exception.service.custom.UserNotSignedUpException;
import com.mindtree.sellyourfurniture.repository.FurnitureImageRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.OrderItemRepository;
import com.mindtree.sellyourfurniture.repository.ReviewRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.IFurnitureService;

@Service
@Transactional
public class FurnitureServiceImpl implements IFurnitureService {
	Pattern pattern;
	Matcher match;
	boolean result;
	@Autowired
	private FurnitureRepository furnitureRepo;

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;
	@Autowired
	private FurnitureImageRepository furnitureImageRepo;

	@Autowired
	private UserRepository userRepo;

	public static byte[] compressBytes(byte[] data) 
	{
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}

		return outputStream.toByteArray();
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

	@Override
	public String addFurniture(PostFurnitureDTO furnitureDTO) throws SellYourFurnitureServiceException {
		Furniture furniture = new Furniture();

		User u = userRepo.findByuserEmail(furnitureDTO.getUserEmail())
				.orElseThrow(() -> new UserNotSignedUpException("Please Sign up First"));

		pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
		match = pattern.matcher(furnitureDTO.getFurnitureType());
		result = match.matches();
		if (!result) {
			throw new InvalidFurnitureTypeException(" type should  contains characters and spaces.");
		}

		furniture.setFurnitureType(furnitureDTO.getFurnitureType());
		furniture.setFurnitureMaterial(furnitureDTO.getFurnitureMaterial());

		if (furnitureDTO.getFurniturePrice() <= 0) {
			throw new InvalidPriceException("Price  should be greater than 0 .");
		}
		furniture.setFurniturePrice(furnitureDTO.getFurniturePrice());

		furniture.setFurnitureShippingType(furnitureDTO.getFurnitureShippingType());
		if (furnitureDTO.getFurnitureShippingType().equals("Paid")) {

			if (furnitureDTO.getFurnitureShippingCharge() <= 0) {

				throw new InvalidPriceException("Shipping charge  should be  greater than  0.");
			}
		}
		furniture.setFurnitureShippingCharge(furnitureDTO.getFurnitureShippingCharge());
		furniture.setFurnitureDescription(furnitureDTO.getFurnitureDescription());
		furniture.setFurniturePrice(furnitureDTO.getFurniturePrice());

		if (furnitureDTO.getFurnitureQuantity() <= 0) {

			throw new InvalidPriceException("Quntity should be greater than 0");
		}
		furniture.setFurnitureQuantity(furnitureDTO.getFurnitureQuantity());
		furniture.setFurnitureName(furnitureDTO.getFurnitureName());

		furniture.setUser(u);
		furnitureRepo.save(furniture);
		return "Furniture Adevertisement Posted";
	}

	@Override
	public String addImage(MultipartFile file) throws IOException {
		Furniture furniture = new Furniture();
		Furniture listFurniture = furnitureRepo.findTopByOrderByFurnitureIdDesc();

		furniture.setFurnitureId(listFurniture.getFurnitureId());
		FurnitureImage furnitureImage = new FurnitureImage();

		furnitureImage.setImageContent(compressBytes(file.getBytes()));

		furnitureImage.setFurnitureImage(furniture);
		furnitureImageRepo.save(furnitureImage);

		return "Image Added";
	}

	@Override
	public List<DisplayFurnitureDTO> getFurnituresBasedOnSearch(String text) throws SellYourFurnitureServiceException {


		List<Furniture> furnitures = new ArrayList<Furniture>();
		furnitures = furnitureRepo.findAll();
		List<Furniture> results = new ArrayList<Furniture>();

		for (int i = 0; i < furnitures.size(); i++) {
			String desc = furnitures.get(i).toString().replaceAll("[^a-zA-Z0-9]", " ");
			desc = desc.trim().replaceAll(" +", " ");
			String[] words = desc.split(" ");
			for (int j = 0; j < words.length; j++) {
				if (words[j].toLowerCase().equals(text.toLowerCase())) {
					if (!results.contains(furnitures.get(i))) {
						results.add(furnitures.get(i));

						break;
					}
				}
			}
		}


		if (results.isEmpty()) {
			throw new NoResultsFurnitureException("Your text does not match our results!");
		}
		List<DisplayFurnitureDTO> furnitureList = new ArrayList<DisplayFurnitureDTO>();
		for (Furniture furniture : results) {
			List<FurnitureImage> image = furnitureImageRepo.findByFurnitureImage(furniture);
			if (!image.isEmpty()) {
				FurnitureImage imageOne = image.get(0);

				DisplayFurnitureDTO furnitureDto = new DisplayFurnitureDTO(furniture.getFurnitureId(),
						furniture.getFurnitureName(), furniture.getFurniturePrice(),

						decompressBytes(imageOne.getImageContent()), furniture.getFurnitureMaterial(),
						furniture.getFurnitureType(), furniture.getFurnitureShippingType());

				furnitureList.add(furnitureDto);
			}
		}
		return furnitureList;
	}

	@Override
	public List<DisplayFurnitureDTO> getAllFurnitures() throws SellYourFurnitureServiceException {

		List<Furniture> furnitures = furnitureRepo.findTop8ByOrderByFurnitureIdDesc();

		List<DisplayFurnitureDTO> furnitureList = new ArrayList<DisplayFurnitureDTO>();

		List<Furniture> furnitureFiltered = furnitures.stream()
				.filter(furniture -> furniture.getFurnitureQuantity() > 0).collect(Collectors.toList());

		if (furnitureFiltered.isEmpty())
			throw new NoResultsFurnitureException("No furnitures are available right now");
		for (Furniture furniture : furnitureFiltered) {

			List<FurnitureImage> image = furnitureImageRepo.findByFurnitureImage(furniture);

			if (!image.isEmpty()) {
				FurnitureImage imageOne = image.get(0);
				DisplayFurnitureDTO furnitureDto = new DisplayFurnitureDTO(furniture.getFurnitureId(),
						furniture.getFurnitureName(), furniture.getFurniturePrice(),
						decompressBytes(imageOne.getImageContent()), furniture.getFurnitureMaterial(),
						furniture.getFurnitureType(), furniture.getFurnitureShippingType());
				furnitureList.add(furnitureDto);
			}

		}

		return furnitureList;
	}

	@Override
	public ViewProductDTO viewProduct(int furnitureId) throws SellYourFurnitureServiceException {

		Furniture furniture = furnitureRepo.findByFurnitureId(furnitureId);
		List<FurnitureImage> image = furnitureImageRepo.findByFurnitureImage(furniture);
		List<byte[]> imageResult = new ArrayList<byte[]>();
		for (FurnitureImage img : image) {
			imageResult.add(decompressBytes(img.getImageContent()));
		}

		List<String> users = new ArrayList<String>();
		List<DisplayReviewDTO> reviewList = new ArrayList<DisplayReviewDTO>();
		float avgRating = 0;
		List<OrderItem> orders = orderItemRepo.findByFurniture(furniture);
		if (!orders.isEmpty()) {
			for (OrderItem order : orders) {
				users.add(order.getOrder().getOrderUser().getUserEmail());
			}
		}
		List<Review> reviews = reviewRepo.findByFurnitureRating(furniture);
		List<String> userReviewed = new ArrayList<String>();
		if (!reviews.isEmpty()) {

			for (Review review : reviews) {
				String userFirstName = review.getUserRating().getUserFirstName();
				String userLastName = review.getUserRating().getUserLastName();
				String userEmail = review.getUserRating().getUserEmail();
				userReviewed.add(review.getUserRating().getUserEmail());
				DisplayReviewDTO reviewDto = new DisplayReviewDTO(review.getReviewId(), userFirstName, userLastName,
						review.getRating(), review.getFeedback(), userEmail);
				reviewList.add(reviewDto);
			}

			avgRating = (float) (reviews.stream().map(Review::getRating).reduce(0, Integer::sum)) / reviews.size();
		}
		ViewProductDTO product = new ViewProductDTO(furniture.getFurnitureId(), furniture.getFurnitureName(),
				furniture.getFurnitureType(), furniture.getFurnitureMaterial(), furniture.getFurniturePrice(),
				furniture.getFurnitureShippingType(), furniture.getFurnitureShippingCharge(),
				furniture.getFurnitureDescription(), imageResult, reviewList, users, userReviewed, avgRating);

		return product;
	}

	

}
