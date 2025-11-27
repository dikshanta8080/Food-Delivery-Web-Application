package com.dikshanta.food.delivery.foodDeliveryBackend.utils;

public class Html {
    public static String getPasswordResetHtml(String otp) {
        return String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Password Reset OTP</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      background-color: #f7f7f7;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    .container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 50px auto;\n" +
                "      background-color: #ffffff;\n" +
                "      border-radius: 8px;\n" +
                "      padding: 30px;\n" +
                "      box-shadow: 0 0 10px rgba(0,0,0,0.1);\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #333333;\n" +
                "      font-size: 24px;\n" +
                "    }\n" +
                "    p {\n" +
                "      font-size: 16px;\n" +
                "      color: #555555;\n" +
                "      line-height: 1.5;\n" +
                "    }\n" +
                "    .otp {\n" +
                "      display: inline-block;\n" +
                "      font-size: 28px;\n" +
                "      font-weight: bold;\n" +
                "      color: #ffffff;\n" +
                "      background-color: #4CAF50;\n" +
                "      padding: 10px 20px;\n" +
                "      border-radius: 6px;\n" +
                "      margin: 20px 0;\n" +
                "      letter-spacing: 4px;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      font-size: 14px;\n" +
                "      color: #999999;\n" +
                "      margin-top: 30px;\n" +
                "      border-top: 1px solid #eeeeee;\n" +
                "      padding-top: 10px;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <h1>Password Reset Request</h1>\n" +
                "    <p>Hello,</p>\n" +
                "    <p>You recently requested to reset your password. Please use the following One-Time Password OTP to reset your password. This OTP is valid for <strong>10 minutes</strong>.</p>\n" +
                "    \n" +
                "    <div class=\"otp\">%S</div>\n" +
                "    \n" +
                "    <p>If you did not request a password reset, please ignore this email.</p>\n" +
                "    \n" +
                "    <div class=\"footer\">\n" +
                "      &copy; 2025 Your Company. All rights reserved.\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n", otp);
    }

}
