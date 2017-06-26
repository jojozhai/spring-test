/**
 * 
 */
package com.lesson.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import com.lesson.dto.Sex;

/**
 * @author zhailiang
 *
 */
@Entity
public class Author extends DomainImpl {
	
	private String name;
	
	@NotBlank
	private String email;
	
	@Column(columnDefinition = "INT(3)")
	private int age;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Embedded
	private Address address;
	
	@ElementCollection
	private List<String> hobbies;
	
	@ElementCollection
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "author")
//	@OrderBy("book.name ASC")
	private List<BookAuthor> books;
	
	@OneToOne
	private AuthorInfo info;
	
	public List<BookAuthor> getBooks() {
		return books;
	}

	public void setBooks(List<BookAuthor> books) {
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public AuthorInfo getInfo() {
		return info;
	}

	public void setInfo(AuthorInfo info) {
		this.info = info;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
