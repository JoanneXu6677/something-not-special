package com.share.entity;

public class SecondHand {
	public int itemId;
	public int price;
	public String pcPath;
	public String description;
	public int userId;
	public SecondHand(int itemId, int price, String pcPath, String description, int userId) {
		super();
		this.itemId = itemId;
		this.price = price;
		this.pcPath = pcPath;
		this.description = description;
		this.userId = userId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPcPath() {
		return pcPath;
	}
	public void setPcPath(String pcPath) {
		this.pcPath = pcPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + itemId;
		result = prime * result + ((pcPath == null) ? 0 : pcPath.hashCode());
		result = prime * result + price;
		result = prime * result + userId;
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
		SecondHand other = (SecondHand) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (itemId != other.itemId)
			return false;
		if (pcPath == null) {
			if (other.pcPath != null)
				return false;
		} else if (!pcPath.equals(other.pcPath))
			return false;
		if (price != other.price)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SecondHand [itemId=" + itemId + ", price=" + price + ", pcPath=" + pcPath + ", description="
				+ description + ", userId=" + userId + "]";
	}
	
}
