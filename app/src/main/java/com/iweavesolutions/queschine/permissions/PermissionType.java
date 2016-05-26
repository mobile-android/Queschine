package com.iweavesolutions.queschine.permissions;

import android.Manifest;

/**
 * Created by raaj.gopal on 17/05/16.
 */
public enum  PermissionType {

    READ_CONTACTS(Manifest.permission.READ_CONTACTS),
    READ_PHONE_STATE(Manifest.permission.READ_PHONE_STATE),
    CAMERA(Manifest.permission.CAMERA),
    GET_ACCOUNTS(Manifest.permission.GET_ACCOUNTS),
    ACCESS_COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
    ACCESS_FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION),
    WRITE_EXTERNAL_STORAGE(Manifest.permission.WRITE_EXTERNAL_STORAGE),
    READ_SMS(Manifest.permission.READ_SMS),
    RECEIVE_SMS(Manifest.permission.RECEIVE_SMS);

    String permission;

    PermissionType(String permission){
        this.permission = permission;
    }

    public static PermissionType getPermissionEnum(String permission){
        if(permission != null){
            for(PermissionType permissionType : PermissionType.values()){
                if(permissionType.permission.equals(permission)){
                    return permissionType;
                }
            }
        }
        return null;
    }
}
