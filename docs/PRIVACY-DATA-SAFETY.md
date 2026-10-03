# Google Play Data Safety & Privacy Policy Guide

**Application:** জমির হিসাব (Land Calculator BD)  
**Package:** `com.vivescriptsolutions.jomirhisab`  
**Developer:** ViveScript Solutions (https://www.vivescriptsolutions.com/)  
**Version:** 1.0.0

---

## ১. সারাংশ (Executive Summary)

"জমির হিসাব" একটি সম্পূর্ণ ক্লায়েন্ট-সাইড এবং **১০০% অফলাইন** ইউটিলিটি অ্যাপ্লিকেশন।
- **কোনো ডেটা সংগ্রহ করা হয় না (Zero Data Collected)**
- **কোনো ডেটা তৃতীয় পক্ষের সাথে শেয়ার করা হয় না (Zero Data Shared)**
- **কোনো ব্যবহারকারী অ্যাকাউন্ট বা লগইনের প্রয়োজন নেই (No Account / Registration)**
- **কোনো ইন্টারনেট বা সংবেদনশীল পারমিশন নেই (No Internet or Dangerous Permissions)**

---

## ২. পারমিশন পর্যালোচনা (Permissions Review)

অ্যাপ্লিকেশনের `AndroidManifest.xml`-এ কোনো প্রকার সংবেদনশীল বা ডেটা এক্সেস পারমিশন যুক্ত করা হয়নি:
- ❌ `android.permission.INTERNET` (অনুপস্থিত - অ্যাপ বিমান মোডেও সম্পূর্ণ সচল)
- ❌ `ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION` (অনুপস্থিত)
- ❌ `READ_CONTACTS` / `READ_CALL_LOG` (অনুপস্থিত)
- ❌ `CAMERA` / `RECORD_AUDIO` (অনুপস্থিত)
- ❌ `READ_EXTERNAL_STORAGE` / `READ_MEDIA_*` (অনুপস্থিত)

---

## ৩. Google Play Console Data Safety ডিক্লারেশন নির্দেশিকা

Play Console-এ **App Content -> Data safety** ফর্ম পূরণের সময় নিচের তথ্যগুলো হুবহু প্রদান করতে হবে:

| প্রশ্ন (Question) | সঠিক উত্তর | মন্তব্য |
| :--- | :--- | :--- |
| **Does your app collect or share any of the required user data types?** | **No** | কোনো প্রকার ব্যক্তিগত বা ডিভাইস ডেটা সংগৃহীত হয় না। |
| **Is all of the user data collected by your app encrypted in transit?** | **N/A** (No data collected) | যেহেতু কোনো ডেটা পাঠানো হয় না। |
| **Do you provide a way for users to request that their data be deleted?** | **N/A** | যেহেতু কোনো অ্যাকাউন্ট বা ডেটা নেই। |

---

## ৪. অন্যান্য Play Console App Content ডিক্লারেশন

1. **Ads (বিজ্ঞাপন):**  
   - উত্তর: **No, my app does not contain ads** (V1-এ কোনো বিজ্ঞাপন নেই)।
2. **App Access (অ্যাপ এক্সেস):**  
   - উত্তর: **All functionality is available without special access restrictions** (লগইন বা ক্রেডেনশিয়াল প্রয়োজন নেই)।
3. **Target Audience and Content:**  
   - বয়সসীমা: **18 and over** অথবা **13 and over** (সাধারণ ইউটিলিটি টুল)।
   - Appeal to children: **No**.
4. **Government Apps:**  
   - উত্তর: **No, this app is NOT developed by or on behalf of a government entity.**
   - অ্যাপের ভেতরে ও বিবরণে স্পষ্ট উল্লেখ রয়েছে যে এটি কোনো সরকারি বা ভূমি মন্ত্রণালয়ের অফিসিয়াল অ্যাপ্লিকেশন নয়।
5. **Financial Features:**  
   - উত্তর: **My app doesn't provide any financial features** (এটি শুধুমাত্র জমি পরিমাপের ক্ষেত্রফল রূপান্তর ক্যালকুলেটর)।

---

## ৫. গোপনীয়তা নীতি (Privacy Policy Text Template)

নিচে ViveScript Solutions-এর ওয়েবসাইটে হোস্ট করার জন্য প্রস্তুতকৃত গোপনীয়তা নীতি দেওয়া হলো:

```markdown
# Privacy Policy for জমির হিসাব (Land Calculator BD)
Last updated: 2026

ViveScript Solutions built the "জমির হিসাব (Land Calculator BD)" app as a Free, Offline tool. This SERVICE is provided by ViveScript Solutions at no cost and is intended for use as is.

### Information Collection and Use
"জমির হিসাব" does not collect, transmit, store, or share any personal identifiable information or device data. All mathematical conversions and inputs are processed strictly on your local device. 

The application requires no network permissions and operates fully in offline or airplane mode.

### Third-Party Services
This application does not integrate any third-party analytics, tracking SDKs, or advertising networks.

### Changes to This Privacy Policy
We may update our Privacy Policy from time to time. You are advised to review this page periodically for any changes.

### Contact Us
If you have any questions or suggestions about our Privacy Policy, do not hesitate to contact us:
Website: https://www.vivescriptsolutions.com/
Email: support@vivescriptsolutions.com
```
