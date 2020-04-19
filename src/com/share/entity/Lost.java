package com.share.entity;

public final class Lost {
	public int lostId; //标志字段4+（1失物招领/2寻物启事）
	public String location;
	public String pcPath;
	public String time;
	public String description;
	public int userId;
	public String contract;
	public Lost(int lostId, String location, String pcPath, String time, String description, int userId,
			String contract) {
		super();
		this.lostId = lostId;
		this.location = location;
		this.pcPath = pcPath;
		this.time = time;
		this.description = description;
		this.userId = userId;
		this.contract = contract;
	}
	public int getLostId() {
		return lostId;
	}
	public void setLostId(int lostId) {
		this.lostId = lostId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPcPath() {
		return pcPath;
	}
	public void setPcPath(String pcPath) {
		this.pcPath = pcPath;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + lostId;
		result = prime * result + ((pcPath == null) ? 0 : pcPath.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Lost other = (Lost) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (lostId != other.lostId)
			return false;
		if (pcPath == null) {
			if (other.pcPath != null)
				return false;
		} else if (!pcPath.equals(other.pcPath))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LostAndFound [lostId=" + lostId + ", location=" + location + ", pcPath=" + pcPath + ", time=" + time
				+ ", description=" + description + ", userId=" + userId + ", contract=" + contract + "]";
	}
}
