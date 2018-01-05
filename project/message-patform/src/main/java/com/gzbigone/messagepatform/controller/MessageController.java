package com.gzbigone.messagepatform.controller;

import com.alibaba.fastjson.JSONArray;
import com.gzbigone.messagepatform.entity.Approval;
import com.gzbigone.messagepatform.entity.Message;
import com.gzbigone.messagepatform.entity.Recipient;
import com.gzbigone.messagepatform.entity.User;
import com.gzbigone.messagepatform.service.impl.RegService;
import com.gzbigone.messagepatform.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegService regService;

    /**
     * 发起短信
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/Message", method = RequestMethod.POST)
    public Object Index(@RequestParam("modelId")Integer modelId, @RequestParam("messageDescription")String messageDescription,
                        @RequestParam("messageContent")String messageContent,
                        @RequestParam("sendDate")String sendDate,
                        @RequestParam("recipientList")String[] recipientList, @RequestParam("approvalList")String[] approvalList,
                        HttpServletRequest request, HttpServletResponse response) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Message message = new Message();
            message.setModelId(modelId);
            message.setMessageDescription(messageDescription);
            message.setMessageContent(messageContent);
            message.setSendDate(simpleDateFormat.parse(sendDate));
            message.setSponsorDate(new Date());
            for(String recipients :recipientList){
                String[] split = recipients.split("-");
                Recipient recipient = new Recipient();
                recipient.setMessageId(1);
                recipient.setUserName(split[0]);
                recipient.setUserTel(Integer.valueOf(split[1]));
            }
            for(String approvals :approvalList){
                String[] split = approvals.split("-");
                Approval approval = new Approval();
                approval.setMessageId(1);
//                approval.setUserId(split[0]);
//                approval.setSort(split[1]);

                approval.setStatus(1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }





        return "";
    }

}
