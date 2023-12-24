package com.example.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CURRENCY") 
public class Currency implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 595311266845495462L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="CODE", length=30) 
    private String code;

    @Column(name="SYMBOL", length=30) 
    private String symbol;
    
    @Column(name="rate", length=30) 
    private String rate;
    
    @Column(name="DESCRIPTION", length=30) 
    private String description;

    
    @Column(name="RATE_FLOAT", precision = 20, scale = 4)
    private BigDecimal rateFloat;

    @Column(name="chinese_name", length=30) 
    private String chineseName;
    
    @Column(name="UPD_DATE") 
    private Timestamp updDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
     
    
	
}

