package com.qualitest.poc.model;

public class Keys {
	
	private String itemId;
	private String keyText;
	private String key;
	
	public String getItemId() {
		return itemId;
	}
	public Keys(String itemId, String key, String keyText) {
		this.itemId = itemId;
		this.keyText = keyText;
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getKeyText() {
		return keyText;
	}
	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

}
