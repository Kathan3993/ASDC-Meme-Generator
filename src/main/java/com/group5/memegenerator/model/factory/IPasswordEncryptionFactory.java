package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.model.IPasswordEncryption;

public interface IPasswordEncryptionFactory {
    IPasswordEncryption makePasswordEncryption();
}
