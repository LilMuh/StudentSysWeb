# StudentSysWeb
Turning a java-based student system ([StudentSys](https://github.com/LilMuh/StudentSystem/tree/main)) into a practical website
### 2023.11.02
  1. Using Vue.js to build up front-end(static) web page
  2. Using Java to build up back-end system(Common, Service, Controller, Entity, Mapper)
  3. Using Springboot and Axios as the support to deploy the connection between front-end and back-end database.

### 2023.11.03
  1. Created a new page (LoginView.vue) before HomeView and level up the router.
  2. Added "Exception" directory into back-end system to show error messages
  3. Optimized "Add" funtion in AdminView.vue and some methods to verify input's format and uniqueness(username, workerID, and phone)
  4. Fully developed the login function. On the login page, it will detect if username and password are both typed in, and then find the information in database through Springboot

### 2023.11.04
  1. Created a page (RegisterView.vue) for new admins to sign up
  2. Add logics to check whether register information is under a correct format.
  3. Created new UI (checkmark and crossmark) beside the input box to notify whether the input format is correct/valid
  4. Add new UI to add method on AdminView.vue page to notify if the input is valid on format.

### 2023.11.05-07
  1. Optimized the visual effect and website layout by adding more interactive APIs.
  2. Optimized efficiency, layout, and fixed technical issues.

### 2023.11.08
  1. Upgraded the authority layers using JWT token and secure the website from direct visit (every manipulation goes after user logged in).
  2. Created pages and APIs to the account features for "forget username or password" and user will be able to set new password after they get autorized by workerID and Phone number.

### 2023.11.09
  1. Upgraded the authority layers, adding JwtInterceptor to authorize every command. (Large step on web security)

### 2023.11.10
  1. Initialized a new page (EssayView) to upload academic essays as portfolios on the website.

### 2023.11.11
  1. Further completion on EssayView and back-end APIs
  2. Developed upload API for uploading features in essays
