package com.mindtree.sellyourfurniture.dto;

import java.util.Arrays;

public class DisplayFurnitureDTO {
	
private int furnitureId;
    
    private String furnitureType;
    
    private double furniturePrice;
    
    private byte[] imageContent;

 private String furnitureMaterial;
 
 private String furnitureType1;
 
 private String furnitureShipping;


    public DisplayFurnitureDTO() {
        super();
        // TODO Auto-generated constructor stub
    }


	

	public DisplayFurnitureDTO(int furnitureId, String furnitureType, double furniturePrice, byte[] imageContent,
			String furnitureMaterial, String furnitureType1, String furnitureShipping) {
		super();
		this.furnitureId = furnitureId;
		this.furnitureType = furnitureType;
		this.furniturePrice = furniturePrice;
		this.imageContent = imageContent;
		this.furnitureMaterial = furnitureMaterial;
		this.furnitureType1 = furnitureType1;
		this.furnitureShipping = furnitureShipping;
	}




	public int getFurnitureId() {
		return furnitureId;
	}


	public void setFurnitureId(int furnitureId) {
		this.furnitureId = furnitureId;
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


	public byte[] getImageContent() {
		return imageContent;
	}


	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}


	public String getFurnitureMaterial() {
		return furnitureMaterial;
	}


	public void setFurnitureMaterial(String furnitureMaterial) {
		this.furnitureMaterial = furnitureMaterial;
	}


	public String getFurnitureType1() {
		return furnitureType1;
	}


	public void setFurnitureType1(String furnitureType1) {
		this.furnitureType1 = furnitureType1;
	}


	public String getFurnitureShipping() {
		return furnitureShipping;
	}




	public void setFurnitureShipping(String furnitureShipping) {
		this.furnitureShipping = furnitureShipping;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + furnitureId;
		result = prime * result + ((furnitureMaterial == null) ? 0 : furnitureMaterial.hashCode());
		long temp;
		temp = Double.doubleToLongBits(furniturePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((furnitureShipping == null) ? 0 : furnitureShipping.hashCode());
		result = prime * result + ((furnitureType == null) ? 0 : furnitureType.hashCode());
		result = prime * result + ((furnitureType1 == null) ? 0 : furnitureType1.hashCode());
		result = prime * result + Arrays.hashCode(imageContent);
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DisplayFurnitureDTO other = (DisplayFurnitureDTO) obj;
		if (furnitureId != other.furnitureId)
			return false;
		if (furnitureMaterial == null) {
			if (other.furnitureMaterial != null)
				return false;
		} else if (!furnitureMaterial.equals(other.furnitureMaterial))
			return false;
		if (Double.doubleToLongBits(furniturePrice) != Double.doubleToLongBits(other.furniturePrice))
			return false;
		if (furnitureShipping == null) {
			if (other.furnitureShipping != null)
				return false;
		} else if (!furnitureShipping.equals(other.furnitureShipping))
			return false;
		if (furnitureType == null) {
			if (other.furnitureType != null)
				return false;
		} else if (!furnitureType.equals(other.furnitureType))
			return false;
		if (furnitureType1 == null) {
			if (other.furnitureType1 != null)
				return false;
		} else if (!furnitureType1.equals(other.furnitureType1))
			return false;
		if (!Arrays.equals(imageContent, other.imageContent))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "DisplayFurnitureDTO [furnitureId=" + furnitureId + ", furnitureType=" + furnitureType
				+ ", furniturePrice=" + furniturePrice + ", imageContent=" + Arrays.toString(imageContent)
				+ ", furnitureMaterial=" + furnitureMaterial + ", furnitureType1=" + furnitureType1 + "]";
	}

 

   


	
}