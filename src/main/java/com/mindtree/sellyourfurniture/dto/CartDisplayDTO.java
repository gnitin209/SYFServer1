package com.mindtree.sellyourfurniture.dto;

public class CartDisplayDTO {
	private int furnitureId;
	private byte[] furnitureDTO;
	private String furnitureName;
	private String furnitureType;
	private double furniturePrice;
	private int furnitureQuanitity;
	private int furnitureLimit;

	public CartDisplayDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CartDisplayDTO(int furnitureId, byte[] furnitureDTO, String furnitureName, String furnitureType,
			double furniturePrice, int furnitureQuanitity, int furnitureLimit) {
		super();
		this.furnitureId = furnitureId;
		this.furnitureDTO = furnitureDTO;
		this.furnitureName = furnitureName;
		this.furnitureType = furnitureType;
		this.furniturePrice = furniturePrice;
		this.furnitureQuanitity = furnitureQuanitity;
		this.furnitureLimit = furnitureLimit;
	}


	
	public int getFurnitureId() {
		return furnitureId;
	}



	public void setFurnitureId(int furnitureId) {
		this.furnitureId = furnitureId;
	}



	public byte[] getFurnitureDTO() {
		return furnitureDTO;
	}

	public void setFurnitureDTO(byte[] furnitureDTO) {
		this.furnitureDTO = furnitureDTO;
	}

	public String getFurnitureName() {
		return furnitureName;
	}

	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	public String getFurnitureType() {
		return furnitureType;
	}

	public void setFurnitureType(String furnitureType) {
		this.furnitureType = furnitureType;
	}

	public double getFurniturePrice() {
		return furniturePrice;
	}

	public void setFurniturePrice(double furniturePrice) {
		this.furniturePrice = furniturePrice;
	}

	public int getFurnitureQuanitity() {
		return furnitureQuanitity;
	}

	public void setFurnitureQuanitity(int furnitureQuanitity) {
		this.furnitureQuanitity = furnitureQuanitity;
	}

	public int getFurnitureLimit() {
		return furnitureLimit;
	}

	public void setFurnitureLimit(int furnitureLimit) {
		this.furnitureLimit = furnitureLimit;
	}

}
