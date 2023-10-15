#!/bin/bash

# Defining URL you want to check
url="www.ultimateqa.com"

# Defining output file path
output_file="/Users/watsonmatunhire/IdeaProjects/AutomationTaskSuite/section1/documents/section1_results.txt"

# Checking if script is run with a specific argument (e.g. "--manual")
if [[ "$1" == "--manual" ]]; then
    # Manual execution

    # Use openssl to fetch SSL certificate details
    cert_info=$(openssl s_client -connect "$url:443" 2>/dev/null | openssl x509 -noout -dates)

    # Extract the certificate expiration date
    expiration_date=$(echo "$cert_info" | grep "notAfter" | sed 's/notAfter=//')

    # Converting expiration date to a human-readable format
    expiration_date_human=$(date -j -f "%b %d %T %Y %Z" "$expiration_date" '+%Y-%m-%d %H:%M:%S')

    # Print expiration date
    echo "SSL certificate for $url expires on: $expiration_date_human"

    # Save results to a text file
    echo "SSL certificate for $url expires on: $expiration_date_human" >> $output_file
else
    # Scheduled execution

    # Creating temporary crontab entry
    cron_entry="0 14 * * * $0 --manual >> $output_file 2>&1"

    # Installing temporary crontab entry
    (crontab -l; echo "$cron_entry") | crontab -
fi