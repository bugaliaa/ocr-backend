package com.ocr.ocrbackend.app.service.onboarding;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OTPSenderService {

    @Value("${app.otpHash}")
    private String otpHash;

    public static final String SMS_SENDER_ID = "UNIVST";
    public static final String SMS_TYPE = "Transactional";

    public static final String ENTITY_ID = "1701166237347871714";

    public static final String TEMPLATE_ID = "1707166274766650852";

    @NonNull
    private final AmazonSNSClient amazonSNSClient;

    public void sendOTP(String contactNumber, int countryCode, String secureOTP) {
        PublishRequest publishRequest = new PublishRequest().withMessage(
                        ("<#> %s is your OTP to log into Univest App. Do not " +
                                "share this with anyone. Enjoy Investing!! %s").formatted(
                                secureOTP, otpHash))
                .withPhoneNumber(getNumberWithCountryCode(countryCode,
                        contactNumber))
                .withMessageAttributes(createSMSAttributes());
        amazonSNSClient.publish(publishRequest);
    }

    private Map<String, MessageAttributeValue> createSMSAttributes() {
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID",
                new MessageAttributeValue().withStringValue(SMS_SENDER_ID).withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType",
                new MessageAttributeValue().withStringValue(SMS_TYPE).withDataType("String"));
        smsAttributes.put("AWS.MM.SMS.EntityId",
                new MessageAttributeValue().withStringValue(ENTITY_ID).withDataType("String"));
        smsAttributes.put("AWS.MM.SMS.TemplateId",
                new MessageAttributeValue().withStringValue(TEMPLATE_ID).withDataType("String"));

        return smsAttributes;
    }

    private String getNumberWithCountryCode(int countryCode, String contactNumber) {
        return "+%d%s".formatted(countryCode, contactNumber);
    }
}
