# European Union (EU) Digital Services Act (DSA) & Trader Policy Compliance

**Application:** জমির হিসাব – Land Calculator  
**Package Name:** `com.vivescriptsolutions.jomirhisab`  
**Developer:** ViveScript Solutions  
**Last Updated:** November 2026 (EU Digital Services Act & GDPR Ready)

This guide provides exact, copy-paste ready instructions for complying with the **European Union (EU) Digital Services Act (DSA)**, **EU Trader Status Declaration**, and **GDPR Data Protection Regulations** on Google Play Console.

---

## 1. EU Digital Services Act (DSA) Trader Status Declaration

Google Play requires all developers distributing apps in the European Economic Area (EEA) to declare their **Trader Status** under Article 30 of the EU Digital Services Act.

### Where to configure in Play Console:
1. Open **Google Play Console**.
2. In the left navigation menu, go to **Policy and programs** → **Trader status** (or under Developer Account Details).
3. Select your declaration based on your operational model (Option A or Option B below).

---

### Option A: Declaring as a "Trader" (Recommended for ViveScript Solutions)

Under EU law (Directive 2005/29/EC), any entity acting for purposes relating to their commercial trade, business, craft, or profession is considered a **Trader**. The app is monetized through Google AdMob advertising, so declare as a Trader:

| Google Play Console Field | Value to Enter |
| :--- | :--- |
| **Trader Status** | **Yes, I am a trader** |
| **Legal Entity / Trader Name** | `ViveScript Solutions` |
| **Email Address** | `support@vivescriptsolutions.com` (verified by OTP) |
| **Phone Number** | Verified company phone number (with international dial code) |
| **Physical Address** | Registered business address of ViveScript Solutions |
| **Commercial Register Number / DUNS** | Your state registration number or D-U-N-S number |
| **Website URL** | `https://www.vivescriptsolutions.com/en/apps/jomirhisab` |

> **Note for EU Consumers:** When declared as a Trader, Google Play displays your verified business name, address, email, and phone number publicly on the জমির হিসাব store listing for users browsing from the European Economic Area.

---

### Option B: Declaring as a "Non-Trader" (Not applicable while ads are enabled)

Non-trader status applies only to strictly non-commercial distribution with **zero monetization** (no ads, no in-app purchases, no subscriptions, no paid downloads). Because this app serves Google AdMob ads, **Option A is the correct declaration**.

| Google Play Console Field | Value to Enter |
| :--- | :--- |
| **Trader Status** | **No, I am not a trader** |
| **Certification** | Only if all monetization is removed from the app. |

---

## 2. EU GDPR Compliance & User Data Rights

জমির হিসাব follows **Privacy-by-Design**: it collects no first-party personal data at all. GDPR processing applies only to the embedded Google Mobile Ads SDK, which is gated by Google UMP consent:

### 2.1 Principle of Data Minimization (GDPR Article 5(1)(c))
- **No Personal Identifiable Information (PII):** The app requires zero registration, no legal names, no email addresses, no phone numbers, and no passwords.
- **On-Device Only:** Typed land amounts are processed in volatile memory and never stored, transmitted, or logged.
- **Ad SDK Only:** The sole processing of identifiers (Advertising ID) is performed by Google's SDK for ad serving, and only after UMP consent clearance.

### 2.2 Consent Management (GDPR Article 6(1)(a) / ePrivacy)
- **UMP Consent Gate:** On startup the app requests consent via Google User Messaging Platform. Where consent is required (EEA/UK) and declined or not given, the Mobile Ads SDK is never initialized and no ads load.
- **AdMob Console Prerequisite:** A GDPR consent message must be created for the ad units in the AdMob console (`ca-app-pub-5222053984568989~1169659083`), otherwise EEA ad serving stays disabled by design.
- **No Consent Banner of Our Own Required:** The app sets no cookies and runs no first-party tracking; the UMP form is Google's own consent surface for its SDK.

### 2.3 Right to Erasure / "Right to be Forgotten" (GDPR Article 17)
- **Nothing to Erase First-Party:** The app keeps no accounts, no databases of user data, and no server copies — there is no first-party personal data to delete.
- **Advertising ID Control:** Users can reset or delete their advertising ID at any time via Android **Settings → Privacy → Ads**, which is outside the app and applies device-wide.
- **Uninstall Cleans All:** Uninstalling the app removes 100% of local application records.

---

## 3. EU Consumer Rights & Disclosures (Directive 2011/83/EU)

To comply with EU digital consumer protection directives:
- **Price Disclosure:** The app is 100% free to download and use; it is supported by Google AdMob banner and interstitial ads. No hidden charges, digital goods, subscriptions, or paywalls.
- **Offline Disclosure:** Listings state precisely that the *calculation engine* works offline while ads require connectivity — never "zero internet requirement" for the whole app.
- **Legal Disclaimer:** The in-app Info sheet and listing state that conversions follow the standard Bangladesh survey reference and must be verified with official land records for legal transactions.

---

## 4. Play Console Review Checklist for EU Distribution

- [x] Trader status configured under **Policy and programs > Trader status** (Yes, trader — ad-monetized).
- [x] Privacy Policy accessible via public HTTPS link (`https://www.vivescriptsolutions.com/en/apps/jomirhisab/privacy`).
- [x] Terms of Service accessible via public HTTPS link (`https://www.vivescriptsolutions.com/en/apps/jomirhisab/terms`).
- [x] Data Safety questionnaire answered (AdMob SDK Device IDs + Diagnostics; encrypted in transit).
- [x] Ads + Advertising ID declarations answered Yes (Advertising or marketing).
- [x] Target audience declared as 18+ (or 13+) with no unintentional appeal to young children.
- [x] Permissions restricted to ads-only `INTERNET` / `ACCESS_NETWORK_STATE` / `AD_ID` — zero dangerous permissions.
- [ ] GDPR consent message created in AdMob console for the app's ad units (required before EEA monetization).
