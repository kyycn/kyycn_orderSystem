package com.hsbc.team4.ordersystem.users.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hsbc.team4.ordersystem.common.utils.RedisUtils;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.smsmessage.SendMsg;
import com.hsbc.team4.ordersystem.users.domain.User;
import com.hsbc.team4.ordersystem.users.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kevin
 * @version : 1.0
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.controller
 * @Description : The is a UserController
 * @Date : 2018/8/1
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final IUserService iUserService;
    private final ResponseResults responseResults;
    private final DefaultKaptcha defaultKaptcha;
    private final RedisUtils redisUtils;

    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public UserController(IUserService iUserService, ResponseResults responseResults, DefaultKaptcha defaultKaptcha,  RedisUtils redisUtils) {
        this.iUserService = iUserService;
        this.responseResults = responseResults;
        this.defaultKaptcha = defaultKaptcha;
        this.redisUtils = redisUtils;
    }
    /**
     *  phone
     * @param phone  phone
     * @return ResponseResults
     */
    @ApiOperation(value = "verify phone",notes = " pass a phone param",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/verifyPhone/{phone}")
    public ResponseResults verifyPhone(@PathVariable String phone){
        User user=iUserService.findByPhone(phone);
        if(user!=null){
            return responseResults.responseBySuccess("The phone had been register");
        }
        return responseResults.responseBySuccess("ok");
    }

    /**
     * sendMessage
     * @param map The map is must including phone,msgType and bizType
     * @return ResponseResults
     */
    @ApiOperation(value = "sends verify message",notes = " pass a map",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @PostMapping("/sendMessage")
    public ResponseResults sendMessage(@RequestBody Map<String,String> map){

        SendMsg sendMsg=iUserService.sendMessage(map);
        if(sendMsg!=null){
            return responseResults.responseBySuccess("ok",sendMsg);
        }
        return responseResults.responseByErrorMessage("overtime  Please re-send");
    }

    /**
     *  verifyEmail
     * @param email  email
     * @return ResponseResults
     */
    @ApiOperation(value = "verify email",notes = " pass a email param",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "email",value = "email",dataType="String")
    @GetMapping("/verifyEmail/{email}")
    public ResponseResults verifyEmail(@PathVariable String email){
        User user=iUserService.findByEmail(email);
        if(user!=null){
            return responseResults.responseBySuccess("The email had been register");
        }
        return responseResults.responseBySuccess("ok");
    }

    /**
     *  sendEmail
     * @param email  email
     * @return ResponseResults
     */
    @ApiOperation(value = "send email",notes = " pass a email param",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/sendEmail/{email}")
    public ResponseResults sendEmail(@PathVariable String email){
        return responseResults.responseBySuccess("ok");
    }

    /**
     *  checkVerifyCode
     * @param map The map must be including msgId And verifyCode
     * @return  ResponseResults
     */
    @ApiOperation(value = "submit verify message",notes = " pass a msgId and verifyCode",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "map",value = "map",dataType="Map")
    @GetMapping("/checkVerifyCode")
    public ResponseResults checkVerifyCode(@RequestBody Map<String,String> map){
        return responseResults.responseBySuccess(iUserService.checkVerifyCode(map));
    }

    @ApiOperation(value = "get defaultVerify", httpMethod = "GET", notes = "get defaultVerify")
    @GetMapping("/imageVerifyCode")
    public void imageVerifyCode(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //create verifyCode and save to session
            String createText = defaultKaptcha.createText();
            HttpSession httpSession=httpServletRequest.getSession(true);
            httpSession.removeAttribute("verifyCode");
            httpSession.setAttribute("verifyCode", createText);
            if(!redisUtils.hasKey("verifyCode")){
                redisUtils.delete("verifyCode");
            }
            redisUtils.addValue("verifyCode",createText);
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    @ApiOperation(value = "verify the imageVerifyCode", httpMethod = "GET", notes = "verify the imageVerifyCode", response = ResponseResults.class)
    @GetMapping("/imageVerify/{verifyCode}")
    public ResponseResults verifyCode(@PathVariable String  verifyCode){
        String code= (String) redisUtils.getValue("verifyCode");
        if (!code.equals(verifyCode)) {
            return  responseResults.responseByErrorMessage("The verifyCode is not correct");
        } else {
            return responseResults.responseBySuccess("ok");
        }
    }

    /**
     *  ResponseResults
     * @param user
     * @return ResponseResults
     */
    @ApiOperation(value = "register",notes = "pass username and password",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "user",value = "The user message",dataType="User")
    @PostMapping("/register")
    public ResponseResults register(@RequestBody User user){
        if(iUserService.findByUsername(user.getUsername())!=null){
            return  responseResults.responseByErrorMessage("the username is exist");
        }
        User user1=iUserService.register(user);
        if(user1!=null){
            return  responseResults.responseBySuccess("保存成功",user1);
        }
        return  responseResults.responseByErrorMessage("error please try a again");
    }

    /**
     * login
     * @param user
     * @param request
     * @return ResponseResults
     */
    @ApiOperation(value = "login",notes = "pass username and password",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "user",value = "The user message",dataType="User")
    @PostMapping("/login")
    public ResponseResults login(@ApiParam(required = true,name = "user",value = "login user") @RequestBody User user, HttpServletRequest request){
        try {
            Map<String,Object> resultMap=new HashMap<>();
            final String token = iUserService.login(user.getUsername(),user.getPassword(),request);
            resultMap.put("token",token);
            if(token!=null){
                User user1=iUserService.findByUsername(user.getUsername());
                user1.setLastLoginTime(System.currentTimeMillis());
                User user2=iUserService.updateEntity(user1);
                resultMap.put("user",user2);
                return responseResults.responseBySuccess("succeed",resultMap);
            }
            return responseResults.responseByErrorMessage("The password is not correct，please enter again");
        }catch (Exception e){
            log.warn(e.getMessage());
            return responseResults.responseByErrorMessage("The username or password is not correct,please enter again");
        }
    }


    @ApiOperation(value = "refresh",notes = "pass the oldToken",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "oldToken",value = "The oldToken message",dataType="String")
    @GetMapping("/refresh")
    public ResponseResults refresh(HttpServletRequest request){
        //获取tokenHeader
        final String authHeader = request.getHeader(this.tokenHeader);
        String authToken;
        if(authHeader != null && authHeader.startsWith(tokenHead)){
            // 获取token后面真正的token
            authToken = authHeader.substring(tokenHead.length());
            String token=iUserService.refresh(authToken);
            if(token!=null){
                return responseResults.responseBySuccess("刷新成功",token);
            }
            return responseResults.responseByErrorMessage("刷新失败");
        }
        return responseResults.responseByErrorMessage("刷新失败");
    }

    @ApiOperation(value = "updatePassword",notes = "pass the oldPassword And newPassword",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "map",value = "map must be include id,oldPassword,newPassword",dataType="Map")
    @PostMapping("/updatePassword")
    public ResponseResults updatePassword(@RequestBody Map<String,String> map){
        return responseResults.responseBySuccess(iUserService.updatePassword(map));
    }

    /**
     *  queryUserById
     * @param id the user id
     * @return ResponseResults
     */
    @ApiOperation(value = "queryUserById",notes = "the param is a UserId",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "id",value = "the user id",dataType="String")
    @GetMapping("/{id}")
    public ResponseResults queryUserById(@PathVariable String id){
        User user=iUserService.findById(id);
        if(user!=null){
            return responseResults.responseBySuccess("ok",user);
        }
        return responseResults.responseBySuccess();
    }

    @ApiOperation(value = "updateUser",notes = "the param is a User object",httpMethod = "PUT",response = ResponseResults.class)
    @ApiImplicitParam(name = "user",value = "the user object",dataType="User")
    @PutMapping("/")
    public ResponseResults updateUser(@RequestBody User user){
        User user1=iUserService.updateEntity(user);
        if(user1!=null){
            return responseResults.responseBySuccess("ok",user);
        }
        return responseResults.responseByErrorMessage("update Failure");
    }








}
