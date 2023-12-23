package com.example.domain;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value="Currency form object",description="Currency Object")
public class CurrencyForm {
	
	@ApiModelProperty(value = "幣別代碼",required = true,example = "USD")
    private String code;

	@ApiModelProperty(value = "幣別符號",required = true,example = "$")
    private String symbol;
    
	@ApiModelProperty(value = "匯率字串",required = true,example = "43,693.4630")
    private String rate;
    
	@ApiModelProperty(value = "敘述",required = true,example = "United States Dollar")
    private String description;

	@ApiModelProperty(value = "匯率",required = true,example = "43693.463")
    private BigDecimal rateFloat;

	@ApiModelProperty(value = "幣別中文名稱",required = true,example = "美金")
    private String chineseName;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(BigDecimal rateFloat) {
		this.rateFloat = rateFloat;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

}
