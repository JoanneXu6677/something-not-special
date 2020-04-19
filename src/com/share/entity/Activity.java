package com.share.entity;

public class Activity {
	public int articleId;
	public String topic;
	public String title;
	public String description;
	public int likeCount;
	public int commentCount;
	public String time;
	public String pcPath;
	public int userId;
	public Activity(int articleId, String topic, String title, String description, int likeCount, int commentCount,
			String time, String pcPath, int userId) {
		super();
		this.articleId = articleId;
		this.topic = topic;
		this.title = title;
		this.description = description;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
		this.time = time;
		this.pcPath = pcPath;
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPcPath() {
		return pcPath;
	}
	public void setPcPath(String pcPath) {
		this.pcPath = pcPath;
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
		result = prime * result + articleId;
		result = prime * result + commentCount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + likeCount;
		result = prime * result + ((pcPath == null) ? 0 : pcPath.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
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
		Activity other = (Activity) obj;
		if (articleId != other.articleId)
			return false;
		if (commentCount != other.commentCount)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (likeCount != other.likeCount)
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "activity [articleId=" + articleId + ", topic=" + topic + ", title=" + title + ", description="
				+ description + ", likeCount=" + likeCount + ", commentCount=" + commentCount + ", time=" + time
				+ ", pcPath=" + pcPath + ", userId=" + userId + "]";
	}
	
}
