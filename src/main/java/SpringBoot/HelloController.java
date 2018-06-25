package SpringBoot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.bean.JsonResult;
import SpringBoot.bean.User;
import SpringBoot.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class HelloController {

//	@Autowired
//	private UserService userService;
	
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value = "sayhello", method = RequestMethod.GET)
	public String sayhello(){
		return "Hello SpringBoot !";
	}

    @RequestMapping("hello")
    public List<Map<String, Object>> hello() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT user,password FROM mysql.user ", new Object[]{});
        return list;
    }

}
