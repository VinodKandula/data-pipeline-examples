# Export Emails to Excel

How To Export Emails From Gmail To Excel With Data Pipeline.

## How to run the example
1. Clone the repo.
2. Download the Data Pipeline jar at [North Concepts Inc.][1]
3. Place the **NorthConcepts-DataPipeline-x.x.x.jar** under the */libs* folder.
4. Place the license file under the */resources* folder.
5. Execute **mvn clean** at the parent root directory.
6. Change to the example root directory (e.g., *examples/export-emails-to-excel*) and run **mvn compile**.
7. Run **mvn exec:java**.
9. Check out the [How To Export Emails From Gmail To Excel With Data Pipeline][2] blog for a more detailed explanation.

[1]: https://northconcepts.com/pricing/ "Data Pipeline Download"
[2]: https://northconcepts.com/blog/2017/08/10/export-emails-to-excel/ "How To Export Emails From Gmail To Excel With Data Pipeline"

## Output
Looks something like this:
```sh
-----------------------------------------------
0 - Record (MODIFIED) {
    0:[from]:STRING=[Google <no-reply@accounts.google.com>]:String
    1:[fromName]:STRING=[Google]:String
    2:[fromAddress]:STRING=[no-reply@accounts.google.com]:String
    3:[replyTo]:STRING=[Google <no-reply@accounts.google.com>]:String
    4:[replyToName]:STRING=[Google]:String
    5:[replyToAddress]:STRING=[no-reply@accounts.google.com]:String
    6:[to]:STRING=[user@example.com]:String
    7:[toName]:UNDEFINED=[null]
    8:[toAddress]:STRING=[user@example.com]:String
    9:[sentDate]:DATETIME=[Fri Aug 11 11:12:59 BST 2017]:Date
    10:[subject]:STRING=[How To Export Emails From Gmail To Excel With Data Pipeline]:String
    11:[size]:INT=[12155]:Integer
    12:[folderName]:STRING=[INBOX]:String
    13:[folderFullName]:STRING=[INBOX]:String
    14:[flagAnswered]:BOOLEAN=[false]:Boolean
    15:[flagDeleted]:BOOLEAN=[false]:Boolean
    16:[flagDraft]:BOOLEAN=[false]:Boolean
    17:[flagFlagged]:BOOLEAN=[false]:Boolean
    18:[flagRecent]:BOOLEAN=[false]:Boolean
    19:[flagSeen]:BOOLEAN=[false]:Boolean
    20:[flagUser]:BOOLEAN=[false]:Boolean
    21:[sender]:STRING=[Google <no-reply@accounts.google.com>]:String
    22:[senderName]:STRING=[Google]:String
    23:[senderAddress]:STRING=[no-reply@accounts.google.com]:String
    24:[receivedDate]:DATETIME=[Fri Aug 11 11:13:05 BST 2017]:Date
    25:[contentType]:STRING=[multipart/ALTERNATIVE; boundary=001a113f229a1853530556778f70]:String
    26:[contentID]:UNDEFINED=[null]
    27:[contentLanguage]:UNDEFINED=[null]
    28:[contentMD5]:UNDEFINED=[null]
    29:[disposition]:UNDEFINED=[null]
    30:[encoding]:UNDEFINED=[null]
    31:[fileName]:UNDEFINED=[null]
    32:[description]:UNDEFINED=[null]
    33:[messageID]:STRING=[<wlVKpiQv5W2yZafdfyOuAQ@notifications.google.com>]:String
    34:[messageNumber]:INT=[60]:Integer
    35:[headerDelivered-To]:STRING=[user@example.com]:String
    36:[headerReceived]:ARRAY of STRING=[[by 10.140.96.196 with SMTP id k62csp713462qge; Fri, 11 Aug 2017
 03:13:05 -0700 (PDT), from mail-yw0-x246.google.com (mail-yw0-x246.google.com.
 [2607:f8b0:4002:c05::246]) by mx.google.com with ESMTPS id
 n187si29...294, by mail-yw0-x246.google.com with SMTP id k20so42883023ywe.7 for
 <user@example.com>; Fri, 11 Aug 2017 03:13:05 -0700 (PDT...129]]:ArrayValue
    37:[headerX-Received]:ARRAY of STRING=[[by 10.129.159.4 with SMTP id w4mr12602492ywg.304.1502446385531;
 Fri, 11 Aug 2017 03:13:05 -0700 (PDT), by 10.129.85.141 with SMTP id j135mr9276503ywb.209.1502446385385;
 Fri, 11 Aug 2017 03:13:05 -0700 (PDT)]]:ArrayValue
    38:[headerARC-Seal]:STRING=[i=1; a=rsa-sha256; t=1502446385; cv=none; d=google.com;
 s=arc-20160816;
 b=x3GaXIeY/9gxaIabSdc5YR4nDVhNlWAQQEEFceLN1VorQCVyDF...435]:String
    39:[headerARC-Message-Signature]:STRING=[i=1; a=rsa-sha256; c=relaxed/relaxed; d=google.com;
 s=arc-20160816; h=to:from:subject:message-id:feedback-id:date:mime-version...587]:String
    40:[headerARC-Authentication-Results]:STRING=[i=1; mx.google.com; dkim=pass
 header.i=@accounts.google.com header.s=20161025 header.b=Kcj0T0Ph; spf=pass
 (google.com: domai...454]:String
    41:[headerReturn-Path]:STRING=[<3MYONWQgTDEApq-tgrn0ceeqwpvu.iqqing.eqovctqnwnwmwtqiockn.eqo@gaia.bounces.google.com>]:String
    42:[headerReceived-SPF]:STRING=[pass (google.com: domain of
 3myonwqgtdeapq-tgrn0ceeqwpvu.iqqing.eqovctqnwnwmwtqiockn.eqo@gaia.bounces.google.com
 designates ...209]:String
    43:[headerAuthentication-Results]:STRING=[mx.google.com; dkim=pass header.i=@accounts.google.com
 header.s=20161025 header.b=Kcj0T0Ph; spf=pass (google.com: domain of
 ...447]:String
    44:[headerDKIM-Signature]:STRING=[v=1; a=rsa-sha256; c=relaxed/relaxed; d=accounts.google.com;
 s=20161025; h=mime-version:date:feedback-id:message-id:subject:fr...547]:String
    45:[headerX-Google-DKIM-Signature]:STRING=[v=1; a=rsa-sha256; c=relaxed/relaxed; d=1e100.net;
 s=20161025;
 h=x-gm-message-state:mime-version:date:feedback-id:message-id...559]:String
    46:[headerX-Gm-Message-State]:STRING=[AHYfb5hGM7xMr/IjUvCapGbvvdwK55mGPyGWYLLoOPandiLm0I93b24o
 t06zDlCETkH50YI5YMxMH3X/BO7ww71G]:String
    47:[headerMIME-Version]:STRING=[1.0]:String
    48:[headerDate]:STRING=[Fri, 11 Aug 2017 10:12:59 +0000 (UTC)]:String
    49:[headerX-Notifications]:STRING=[XEAAAAAeUmpb6-bdoZDDy1wlk7cE]:String
    50:[headerX-Account-Notification-Type]:STRING=[28]:String
    51:[headerFeedback-ID]:STRING=[28:account-notifier]:String
    52:[headerMessage-ID]:STRING=[<wlVKpiQv5W2yZafdfyOuAQ@notifications.google.com>]:String
    53:[headerSubject]:STRING=[How To Export Emails From Gmail To Excel With Data Pipeline]:String
    54:[headerFrom]:STRING=[Google <no-reply@accounts.google.com>]:String
    55:[headerTo]:STRING=[user@example.com]:String
    56:[headerContent-Type]:STRING=[multipart/alternative; boundary="001a113f229a1853530556778f70"]:String
    57:[multipartContentType]:STRING=[multipart/ALTERNATIVE; boundary=001a113f229a1853530556778f70]:String
    58:[contentFileName]:ARRAY of UNDEFINED=[[null, null]]:ArrayValue
    59:[contentContentType]:ARRAY of STRING=[[TEXT/PLAIN; charset=UTF-8; delsp=yes; format=flowed, TEXT/HTML; charset=UTF-8]]:ArrayValue
    60:[contentDisposition]:ARRAY of UNDEFINED=[[null, null]]:ArrayValue
    61:[contentSize]:ARRAY of INT=[[1604, 5030]]:ArrayValue
    62:[content]:ARRAY of STRING=[[Have you ever wanted to pull emails into Excel for analysis?  Maybe you need to find the top companies contacting you for your sales team.  Maybe you need to perform text or sentiment analysis on the contents of your messages.  Or maybe you’re creating visualizations to better understand who’s email you.  This quick guide will show you how to use Data Pipeline to search and read emails from Gmail or G Suite (formerly Google Apps), process them any way you like, and store them in Excel....4654]]:ArrayValue
}

```