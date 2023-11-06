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

### 2023.11.05
  1. Optimized the visual effect and website layout by adding more interactive APIs.
