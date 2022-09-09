package ru.yandex.incoming34.dto;

public class ProductBriefDto {

	private Long id;
	private String name;
	private Integer price;

	public ProductBriefDto() {

	}

	public ProductBriefDto(Long id, String name, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "ProductBriefDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}
