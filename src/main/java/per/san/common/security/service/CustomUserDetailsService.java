//package per.san.common.security.service;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * description:
// *
// * @author shencai.huang@hand-china.com
// * @date 5/23/2019 09:45
// * lastUpdateBy: shencai.huang@hand-china.com
// * lastUpdateDate: 5/23/2019
// */
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        return new User(username, "admin", grantedAuthorities);
//    }
//}
