package com.erin.movie.userManagement.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.erin.movie.userManagement.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//db-> controller  View layer와 데이터를 주고받을 때 
@Data
@NoArgsConstructor
public class UserDto implements Serializable{
    private Long userId;
    
    @Size(min = 4, max = 20)
    @NotBlank(message = "필수 입력 항목입니다")
    @Pattern(regexp="^[a-zA-Z0-9]{4,20}",message = "이름을 4~20자 이내로 입력해주세요.")
	private String username;
    
    @Size(min = 4, max = 20)
    @NotBlank(message = "필수 입력 항목입니e")
    @Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
	private String password;
    
    @Size(min = 2, max = 20)
    @NotBlank(message = "필수 입력 항목입니다")
	private String firstname;
    
    @Size(min = 2, max = 20)
    @NotBlank(message = "필수 입력 항목입니다")
	private String lastname;
    
   //^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$ 
	@Pattern(regexp = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "이메일 형식에 맞지 않습니다.")
	private String email;
	
	private String role;

	@Builder
    public UserDto(Long id, String username, String password,String firstname, String lastname, String email, String role) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname= lastname;
        this.email = email;
        this.role = role;
    }
	
	public User toEntity(){
        return User.builder()
                .id(userId)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .role(role)
                .build();
    }
	
	public void updateInfo(UserDto user) {
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
	}
}
