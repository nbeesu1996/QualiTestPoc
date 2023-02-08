package com.qualitest.poc.model;




public class Rules  {
	
	//private int id;
	
	private String ruleName;
	
	private Boolean flag;
	
	Rules(String ruleName , Boolean flag )
	{
	this.ruleName = ruleName;
	this.flag =  flag;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	



	

}
