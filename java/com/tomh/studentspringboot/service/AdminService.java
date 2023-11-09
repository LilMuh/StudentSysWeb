package com.tomh.studentspringboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tomh.studentspringboot.entity.Admin;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.exception.CustomException;
import com.tomh.studentspringboot.mapper.AdminDao;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

    private static boolean checkUsername(String username) {
        int len = username.length();
        // If the length() is 3~15
        if (len < 3 || len > 15) {
            return false;
        }

        // If the chars in 'a'~'z' 'A'~'Z' '0'~'9'
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }

        // If there is at least one letter
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        // length should be 10 digits
        int len = phoneNumber.length();
        if (len != 10) {
            return false;
        }
        // can not start with 0
        if (phoneNumber.startsWith("0")) {
            return false;
        }
        // all digits must be numbers
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        // If everything looks fine, return true
        return true;
    }

    // Check if WorkerID is in correct format
    private static boolean checkWorkerID(String workerID) {
        // workerID must be at length of 18
        int len = workerID.length();
        if (len != 18) {
            return false;
        }
        // workerID can not start with 0
        if (workerID.startsWith("0")) {
            return false;
        }
        // First 17 digits must be numbers
        for (int i = 0; i < workerID.length() - 1; i++) {
            char c = workerID.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        // The last digit can be either number, x or X
        char lastDigit = workerID.charAt(len - 1);
        if ((lastDigit == 'x') || (lastDigit == 'X') || (lastDigit >= '0' && lastDigit <= '9')) {
            // If everything looks fine, return true
            return true;
        }
        // Otherwise, return false
        return false;
    }


    @Resource
    private AdminDao adminDao;

    public List<Admin> getAdmin() {
        return adminDao.selectAll();
    }

    public PageInfo<Admin> search(Params params) {
        // start page search
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // automatically set
        List<Admin> list = adminDao.search(params);
        return PageInfo.of(list);
    }

    public void add(Admin admin) {
        // Determine if any info is not provided
        if (
                (admin.getUsername() == null || "".equals(admin.getUsername())) ||
                        (admin.getPassword() == null || "".equals(admin.getPassword())) ||
                        (admin.getWorkerID() == null || "".equals(admin.getWorkerID())) ||
                        (admin.getPhoneNumber() == null || "".equals(admin.getPhoneNumber()))
        ) {
            throw new CustomException("Please fill out all the information");
        }
        // Determine if user already exists
        Admin username = adminDao.findByName(admin.getUsername());
        Admin workerID = adminDao.findByWorkerID(admin.getWorkerID());
        Admin phone = adminDao.findByPhone(admin.getPhoneNumber());
        if (username != null) {
            // user exists
            throw new CustomException("The username already exists");
        } else if (workerID != null) {
            // workerID exists
            throw new CustomException("The workerID already exists");
        } else if (phone != null) {
            // phone exists
            throw new CustomException("The phone number already exists");
        } else {
            // Determine if anything is not in format
            if (!checkUsername(admin.getUsername())) {
                throw new CustomException("The username is invalid");
            } else if (!checkWorkerID(admin.getWorkerID())) {
                throw new CustomException("The worker ID is invalid");
            } else if (!checkPhoneNumber(admin.getPhoneNumber())) {
                throw new CustomException("The phone number is invalid");
            } else {
                // Everything is fine, add user
                adminDao.insertSelective(admin);
            }
        }
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }

    public void delete(int id) {
        adminDao.deleteByPrimaryKey(id);
    }

    public Admin login(Admin admin) {
        // 1. determine for null input
        if (
                (admin.getUsername() == null || "".equals(admin.getUsername())) ||
                        (admin.getPassword() == null || "".equals(admin.getPassword()))
        ) {
            throw new CustomException("Please provide the username or password");
        }
        // 2. search admin info in the database
        Admin user = adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (user == null) {
            // If user does not exist, log in fail
            throw new CustomException("The username or password is incorrect");
        } else {
            // If there exist the user, log in successfully
            return user;
        }
    }

    public Admin findUser(Admin admin) {
        // 1. determine for null input
        if (
                (admin.getWorkerID() == null || "".equals(admin.getWorkerID())) ||
                        (admin.getPhoneNumber() == null || "".equals(admin.getPhoneNumber()))
        ) {
            throw new CustomException("Please fill out the information");
        }
        // 2. search admin info in the database
        Admin user = adminDao.findByWorkerIDAndPhone(admin.getWorkerID(), admin.getPhoneNumber());
        if (user == null) {
            // If user does not exist, notify user
            throw new CustomException("Unable to find the user");
        } else {
            // If there exist the user, return user for further manipulation
            return user;
        }
    }
}
