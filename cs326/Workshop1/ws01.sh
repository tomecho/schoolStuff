#!/bin/bash
echo "Username: " >> GitSetupInfo.txt 2>&1
git config user.name >> GitSetupInfo.txt 2>&1
echo "Email: " >> GitSetupInfo.txt 2>&1
git config user.email >> GitSetupInfo.txt 2>&1
git config core.editor >> GitSetupInfo.txt 2>&1
git --version >> GitSetupInfo.txt 2>&1
atom --version >> GitSetupInfo.txt 2>&1
node --version >> GitSetupInfo.txt 2>&1
