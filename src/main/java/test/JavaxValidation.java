package test;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class JavaxValidation {
	public static void main(String[] args) {
		Dog d = new Dog();
		d.setAge(0);
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Dog>> set = validator.validate(null);
		for (ConstraintViolation<Dog> constraintViolation : set) {
			System.out.println(constraintViolation.getMessage());
		}
	}
}

class Dog {
	
	//@NotNull(message="姓名不能为空")
	@NotNull.List({@NotNull(message="第1个元素不能为null"),@NotNull(message="第2个元素不能为null")})
	private String name;

	@Min(value = 1, message = "最少为1")
	@Max(value = 20, message = "最大为20")
	private int age;
	
	//@NotNull.List({@NotNull(message="第1个元素不能为null"),@NotNull(message="第2个元素不能为null")})
	private List<String> list;

	@NotNull(message="获取参数不能为空")
	public String getName() {
		return name;
	}
	
	public void setName(@NotNull(message="获取参数不能为空2")String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}