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

    public static String getPasswordResetConfirmation(String name) {
        String year = String.valueOf(java.time.Year.now().getValue());

        return String.format("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                  <meta charset="utf-8" />
                  <meta name="viewport" content="width=device-width,initial-scale=1" />
                  <title>Password Reset Successful</title>
                  <style>
                    body { margin:0; padding:0; font-family:Arial, sans-serif; background:#f4f6f8; color:#333; }
                    .wrap { max-width:600px; margin:36px auto; background:#ffffff; border-radius:8px; box-shadow:0 6px 18px rgba(0,0,0,0.06); overflow:hidden; }
                    .header { padding:28px 32px; text-align:center; background:linear-gradient(90deg,#1e8af7,#3bb0ff); color:#fff; }
                    .content { padding:28px 32px; }
                    .footer { padding:18px 32px; font-size:13px; color:#94a3b8; border-top:1px solid #f1f5f9; text-align:center; }
                  </style>
                </head>
                <body>
                  <div class="wrap">
                    <div class="header">
                      <h1>Password Reset Successful</h1>
                    </div>
                
                    <div class="content" style="text-align:center;">
                      <h2>You're all set, %s</h2>
                      <p>Your password has been successfully updated.</p>
                      <a href="%s" style="background:#10b981;color:#fff;padding:12px 20px;text-decoration:none;border-radius:8px;font-weight:600;">
                        Sign in to your account
                      </a>
                
                    </div>
                
                    <div class="footer">
                      &copy;  Your Company • Keeping your account secure
                    </div>
                  </div>
                </body>
                </html>
                """, name, "http://localhost:3000");
    }


}
