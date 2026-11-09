# Google Play Data Safety Declaration Guidance — জমির হিসাব

**Application:** জমির হিসাব – Land Calculator  
**Package ID:** `com.vivescriptsolutions.jomirhisab`  
**Developer:** ViveScript Solutions  
**Last Updated:** November 2026 (Version 5.0 / API 36 Compatible)

This document provides exact, copy-paste ready answers for every screen and question in the **Google Play Console Data Safety Form**.

---

## 1. High-Level Summary

| Google Play Console Question | Exact Answer | Reviewer / Codebase Justification |
| :--- | :--- | :--- |
| **Does your app collect or share any of the required user data types?** | **Yes** | The app itself collects nothing, but the embedded Google Mobile Ads SDK collects Device IDs and diagnostics for ad serving. |
| **Is all user data collected encrypted in transit?** | **Yes** | The AdMob SDK transmits over HTTPS. |
| **Do you provide a way for users to request that their data be deleted?** | **No** | No user accounts and no first-party server storage exist; users can reset/delete their advertising ID in Android Settings → Privacy → Ads. |

---

## 2. Step-by-Step Play Console Form Walkthrough

### Screen 1: Data Collection and Security
- **Does your app collect or share any of the required user data types?**  
  👉 Select: **Yes**

- **Is all of the user data collected by your app encrypted in transit?**  
  👉 Select: **Yes**

- **Do you provide a way for users to request that their data be deleted?**  
  👉 Select: **No**  
  *(No accounts or first-party stored data; the Ad ID is user-resettable on-device.)*

### Screen 2: Data Types (collected via Google Mobile Ads SDK)

#### 2.1 Device or other IDs → Device or other IDs
- **Collected:** Yes (Advertising ID, by the AdMob SDK)
- **Shared with third parties:** Yes (with Google for ad serving/reporting)
- **Is this data processed ephemerally?** Yes
- **Is collection required or optional?** Required for ads; ads are gated by UMP consent where applicable
- **Why is this user data collected?**
  - Select: **Advertising or marketing**

#### 2.2 App info and performance → Diagnostics / Crash logs
- **Collected:** Yes (by the AdMob SDK internally)
- **Shared with third parties:** Yes (with Google)
- **Is this data processed ephemerally?** No
- **Why is this user data collected?**
  - Select: **Advertising or marketing**, **Analytics** (SDK-internal)

### What the app itself collects: nothing
- No accounts, no registration, no analytics SDK of our own, no crash reporter of our own.
- Typed land amounts are processed in memory on-device and never leave the phone.
- Copy/share actions use the standard Android clipboard and share sheet only.

---

## 3. Data Types Explicitly NOT Collected

### 3.1 Location
- **Collected:** **No**
- Explanation: *"The app has no location permission and no location API usage. Ads may use coarse signals per Google's own policy, declared under Device IDs above."*

### 3.2 Personal Info (name, email, phone, address)
- **Collected:** **No**
- Explanation: *"No registration or login exists; the app never asks for identity information."*

### 3.3 Photos and Videos / Audio / Files
- **Collected:** **No**
- Explanation: *"No camera, microphone, or storage permissions; no media is accessed."*

### 3.4 Messages / Contacts / Calendar
- **Collected:** **No**

---

## 4. Copy-Paste Reviewer Defense / Appeal Template

If a Google Play reviewer raises an inquiry regarding data safety:

> **To Google Play Review Team:**  
> জমির হিসাব (`com.vivescriptsolutions.jomirhisab`) is a single-screen offline land unit converter.  
> 1. **Zero First-Party Collection:** The app collects, stores, and shares no user data itself — no accounts, no analytics, no crash SDK, no servers. All conversions run in on-device memory.  
> 2. **Ad SDK Disclosure:** The only data collection comes from the embedded Google Mobile Ads SDK (banner + interstitial), which collects the Advertising ID and diagnostics for ad serving. This is declared as Device IDs + Diagnostics, encrypted in transit.  
> 3. **Consent Gating:** Ads initialize only after Google UMP consent clearance; users who decline (EEA/UK) never load the SDK or see ads.  
> 4. **Minimal Permissions:** Only `INTERNET`, `ACCESS_NETWORK_STATE`, and `AD_ID` for ads; zero dangerous permissions.  
> Therefore, জমির হিসাব complies fully with Google Play Data Safety guidelines.
