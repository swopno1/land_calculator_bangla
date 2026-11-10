# Google Play Data Safety & Privacy Policy Guide

**Application:** জমির হিসাব (Land Calculator BD)  
**Package:** `com.vivescriptsolutions.jomirhisab`  
**Developer:** ViveScript Solutions (https://www.vivescriptsolutions.com/en/apps/jomirhisab)  
**Version:** 5.0

---

## ১. সারাংশ (Executive Summary)

"জমির হিসাব" একটি ক্লায়েন্ট-সাইড ইউটিলিটি অ্যাপ — সব গাণিতিক হিসাব ডিভাইসেই হয়,
কোনো ব্যবহারকারী অ্যাকাউন্ট বা লগইনের প্রয়োজন নেই।
- **অ্যাপ নিজে কোনো ব্যক্তিগত ডেটা সংগ্রহ বা শেয়ার করে না** (no accounts, no analytics SDK of our own)
- **Google AdMob SDK** (ব্যানার + ইন্টারস্টিশিয়াল) বিজ্ঞাপন পরিবেশনের জন্য
  বিজ্ঞাপন আইডি ও ডায়াগনস্টিক ডেটা সংগ্রহ করে — নিচের Data Safety উত্তর সেই অনুযায়ী
- **UMP সম্মতি ব্যবস্থা** (EEA/UK) — সম্মতি ছাড়া বিজ্ঞাপন SDK চালু হয় না
- **কোনো বিপজ্জনক (dangerous) পারমিশন নেই** — শুধু বিজ্ঞাপনের জন্য
  `INTERNET`, `ACCESS_NETWORK_STATE` ও `AD_ID`

---

## ২. পারমিশন পর্যালোচনা (Permissions Review)

`AndroidManifest.xml`-এ শুধুমাত্র বিজ্ঞাপনের প্রয়োজনে এই পারমিশনগুলো আছে:
- ✅ `android.permission.INTERNET` (শুধুমাত্র AdMob বিজ্ঞাপন লোডের জন্য; হিসাব ইঞ্জিন অফলাইনেই চলে)
- ✅ `ACCESS_NETWORK_STATE` (AdMob SDK-এর জন্য)
- ✅ `com.google.android.gms.permission.AD_ID` (বিজ্ঞাপন আইডি)
- ❌ `ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION` (অনুপস্থিত)
- ❌ `READ_CONTACTS` / `READ_CALL_LOG` (অনুপস্থিত)
- ❌ `CAMERA` / `RECORD_AUDIO` (অনুপস্থিত)
- ❌ `READ_EXTERNAL_STORAGE` / `READ_MEDIA_*` (অনুপস্থিত)

---

## ৩. Google Play Console Data Safety ডিক্লারেশন নির্দেশিকা

Play Console-এ **App Content -> Data safety** ফর্ম পূরণের সময় নিচের তথ্যগুলো হুবহু প্রদান করতে হবে:

| প্রশ্ন (Question) | সঠিক উত্তর | মন্তব্য |
| :--- | :--- | :--- |
| **Does your app collect or share any of the required user data types?** | **Yes** | Google Mobile Ads SDK বিজ্ঞাপনের জন্য ডিভাইস আইডি সংগ্রহ করে। |
| **Is all of the user data collected by your app encrypted in transit?** | **Yes** | AdMob SDK HTTPS ব্যবহার করে। |
| **Do you provide a way for users to request that their data be deleted?** | **No** | কোনো ব্যবহারকারী অ্যাকাউন্ট বা সার্ভার-সাইড ডেটা নেই; বিজ্ঞাপন আইডি ব্যবহারকারী ডিভাইস সেটিংস থেকে রিসেট/মুছে ফেলতে পারেন। |

**সংগৃহীত ডেটার ধরন (Collected via Google Mobile Ads SDK):**
- **Device or other IDs → Device or other IDs** — Collected, for Advertising/Marketing; Ephemeral; required.
- **App info and performance → Diagnostics/Crash logs** — Collected by the SDK internally (optional).

---

## ৪. অন্যান্য Play Console App Content ডিক্লারেশন

1. **Ads (বিজ্ঞাপন):**  
   - উত্তর: **Yes, my app contains ads** (AdMob ব্যানার + ইন্টারস্টিশিয়াল)।
2. **Advertising ID:**  
   - **Yes** → উদ্দেশ্য: **Advertising or marketing**।
3. **App Access (অ্যাপ এক্সেস):**  
   - উত্তর: **All functionality is available without special access restrictions** (লগইন বা ক্রেডেনশিয়াল প্রয়োজন নেই)।
4. **Target Audience and Content:**  
   - বয়সসীমা: **18 and over** অথবা **13 and over** (সাধারণ ইউটিলিটি টুল)।
   - Appeal to children: **No**।
5. **Government Apps:**  
   - উত্তর: **No, this app is NOT developed by or on behalf of a government entity.**
   - অ্যাপের ভেতরে ও বিবরণে স্পষ্ট উল্লেখ রয়েছে যে এটি কোনো সরকারি বা ভূমি মন্ত্রণালয়ের অফিসিয়াল অ্যাপ্লিকেশন নয়।
6. **Financial Features:**  
   - উত্তর: **My app doesn't provide any financial features** (এটি শুধুমাত্র জমি পরিমাপের ক্ষেত্রফল রূপান্তর ক্যালকুলেটর)।

---

## ৫. গোপনীয়তা নীতি (Privacy Policy)

হোস্ট করা গোপনীয়তা নীতির উৎস ফাইল: `store-assets/play-store-metadata/privacy-policy.html`
(প্রকাশিত URL: `https://www.vivescriptsolutions.com/en/apps/jomirhisab/privacy`)।

নীতিতে অবশ্যই এই তিনটি বিষয় থাকতে হবে (বর্তমান HTML-এ আছে):
1. হিসাব ডিভাইসে হয়, আমরা নিজে কোনো ব্যক্তিগত তথ্য সংগ্রহ করি না;
2. Google AdMob বিজ্ঞাপন আইডি ও ডায়াগনস্টিক ডেটা ব্যবহার করতে পারে
   ([Google Advertising Privacy Policy](https://policies.google.com/technologies/ads));
3. `INTERNET` / `ACCESS_NETWORK_STATE` / `AD_ID` পারমিশন শুধু বিজ্ঞাপনের জন্য।
