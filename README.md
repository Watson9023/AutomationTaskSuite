# AutomationTaskSuite

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# SSL Certificate Checker

## Overview

This script fetches SSL certificate details for a given URL, such as https://www.ultimateqa.com and identifies when the certificate expires. You have the option to run it manually or schedule it for automated execution.

### Setting Permissions

To ensure proper access control for your project files, you can use the following `chmod` commands. Please replace the file paths with your actual paths as needed.

To set permissions for the `documents` directory:

```shell
chmod 755 /path/to/your/AutomationTaskSuite/documents/
```

```
chmod 644 /path/to/your/AutomationTaskSuite/documents/section1_results.txt
```

## Making Scripts Executable

To make the `check_certificate.sh` script executable, you can use the following command. Please replace the path with the actual relative path to the script within your project:

```shell
chmod +x /path/to/your/AutomationTaskSuite/section1/scripts/check_certificate.sh
```


## Manual Execution

To run the script manually, use the following command:
```
./check_certificate.sh --manual
```

Running this command will immediately check the SSL certificate and save the result in a specified output file.

## Automated Execution:
For automated certificate checking, the script can be scheduled using cron jobs. A cron job entry has been included within the script to facilitate automated execution. The script will run daily at 14:00 PM.

## How It Works:

The script connects to the specified URL and extracts SSL certificate details using the OpenSSL tool.
It searches for the "notAfter" date in the certificate details to determine the certificate's expiration date.
The expiration date is then converted into a human-readable format.
The script prints the result to the console and saves it in the designated output file.
Scheduled Execution (Cron Job):
The script is equipped with a cron job entry, which enables it to be scheduled for automated execution at specific times. The scheduled task ensures that SSL certificate details are checked regularly.

## Output:                                                                                                                                  
The SSL certificate details, including the certificate's expiration date, are saved in the specified output file at `/documents/section1_results.txt`.
