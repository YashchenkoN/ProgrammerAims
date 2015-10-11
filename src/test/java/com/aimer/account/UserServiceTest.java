////package com.programmer.account;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Collection;
//
//import com.programmer.programmer.Programmer;
//import com.programmer.programmer.service.ProgrammerDetailsService;
//import com.programmer.programmer.service.ProgrammerService;
//import com.programmer.programmer.service.ProgrammerServiceImpl;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceTest {
//
//	@InjectMocks
//	private ProgrammerDetailsService programmerService = new ProgrammerDetailsService();
//
//	@Mock
//	private ProgrammerService programmerRepositoryMock;
//
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//
//	@Test
//	public void shouldInitializeWithTwoDemoUsers() {
//		// act
//		//programmerService.initialize();
//		// assert
////		verify(programmerRepositoryMock, times(2)).add(any(Programmer.class));
//	}
//
//	@Test
//	public void shouldThrowExceptionWhenUserNotFound() {
//		// arrange
//		thrown.expect(UsernameNotFoundException.class);
//		thrown.expectMessage("user not found");
//
//		when(programmerRepositoryMock.findByEmail("user@example.com")).thenReturn(null);
//		// act
//		programmerService.loadUserByUsername("user@example.com");
//	}
//
//	/*@Test
//	public void shouldReturnUserDetails() {
//		// arrange
//		Programmer demoUser = new Programmer("user@example.com", "demoname", "demo", );
//		when(programmerRepositoryMock.findByEmail("user@example.com")).thenReturn(demoUser);
//
//		// act
//		UserDetails userDetails = programmerService.loadUserByUsername("user@example.com");
//
//		// assert
//		assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
//		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
//        assertThat(hasAuthority(userDetails, demoUser.getRole()));
//	}*/
//
//	private boolean hasAuthority(UserDetails userDetails, String role) {
//		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//		for(GrantedAuthority authority : authorities) {
//			if(authority.getAuthority().equals(role)) {
//				return true;
//			}
//		}
//		return false;
//	}
//}
