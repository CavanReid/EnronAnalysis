//Outputs 2 json files:
// "mediumtestset.json"  (1,000 users, 20,000 emails)
// "largetestset.json"   (10,000 users, 200,000 emails)


import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class Generator {
  public static void main(String[] args){
    //Specify the number of accounts to generate. 
    //Number of emails will be this * 20
    String fileName;
    AccountGen accountGen = new AccountGen();
    EmailGen emailGen = new EmailGen();

    fileName = "mediumtestset.json";
    System.out.println("\nGenerating: \"" + fileName + "\"");
    accountGen = new AccountGen(1000);
    System.out.println("Accounts Generated: " + accountGen.getNumberOfAccounts());
    emailGen = new EmailGen(accountGen,fileName);
    System.out.println("Emails Generated: " + emailGen.getNumberOfEmails());
    System.out.println("Finished Generating \"" + fileName + "\"");

    fileName = "largetestset.json";
    System.out.println("\nGenerating: \"" + fileName + "\"");
    accountGen = new AccountGen(10000);
    System.out.println("Accounts Generated: " + accountGen.getNumberOfAccounts());
    emailGen = new EmailGen(accountGen,fileName);
    System.out.println("Emails Generated: " + emailGen.getNumberOfEmails());
    System.out.println("Finished Generating \"" + fileName + "\"");

  }
}

class AccountGen {
  private int accountCount;
  private String[] accounts;

  public AccountGen(){}

  public AccountGen(int accountCount){
    this.accounts = new String[accountCount];
    for (int i = 0;i < accountCount;i++){
      this.accounts[i] = Integer.toString(i) + "@enron.com";
    }
    this.accountCount = accountCount;
  }

  public int getNumberOfAccounts(){
    return this.accounts.length;
  }	

  public String getAccount(int targetAccount){
    return this.accounts[targetAccount];
  }
}

class EmailGen {
  private int emailCount;
  private int accountCount;

  public EmailGen(){}

  public EmailGen(AccountGen accounts, String fileName){
    System.out.println("Generating Emails");
    System.out.print("[");
    try {
      PrintWriter pw = new PrintWriter(fileName);
      pw.close();
    } catch (IOException e) {}
    this.accountCount = accounts.getNumberOfAccounts();
    this.emailCount = this.accountCount * 20;
    try{
      PrintWriter writer = new PrintWriter (new BufferedWriter(new FileWriter(fileName, true)));
      writer.println("[");
      for (int i=0; i<this.emailCount; i++){
        if (i == (int) this.emailCount/20) System.out.print("-");
        if (i == (int) 2*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 3*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 4*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 5*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 6*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 7*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 8*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 9*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 10*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 11*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 12*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 13*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 14*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 15*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 16*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 17*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 18*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 19*(this.emailCount/20)) System.out.print("-");
        if (i == (int) 20*(this.emailCount/20)) System.out.print("-");
        int recipientCount = ThreadLocalRandom.current().nextInt(1,10);
        String emailData = "  {\n    \"sender\": \"" + accounts.getAccount(ThreadLocalRandom.current().nextInt(0,this.accountCount)) + "\",\n    \"recipients\" : [\n";
        for (int j=0; j<recipientCount; j++){
          emailData += "      \"" +  accounts.getAccount(ThreadLocalRandom.current().nextInt(0,this.accountCount))+ "\"";
          if (j != recipientCount - 1) emailData += ",";
          emailData += "\n";
        }
        String timeStamp = generateTimeStamp();
        emailData = emailData + "    ],\n    \"timestamp\":" + timeStamp + "\n  }";
        if (i != this.emailCount - 1) emailData += ",";
        writer.println(emailData);
      }
      writer.print("]");
      writer.close();
    } catch (IOException e){System.out.println("error");};
    System.out.println("] DONE");
  }

  public String generateTimeStamp(){
    int month;
    int day;
    int hour;
    int minute;
    
    String monthString;
    String dayString;
    String hourString;    
    String minuteString;
    String timeStamp;
	
    month = ThreadLocalRandom.current().nextInt(1,12);
    switch (month) {
      case 2: day = ThreadLocalRandom.current().nextInt(1,28); break;
      case 4: day = ThreadLocalRandom.current().nextInt(1,30); break;
      case 6: day = ThreadLocalRandom.current().nextInt(1,30); break;
      case 9: day = ThreadLocalRandom.current().nextInt(1,30); break;
      case 11: day = ThreadLocalRandom.current().nextInt(1,30); break;
      default: day = ThreadLocalRandom.current().nextInt(1,31);
    }
    hour = ThreadLocalRandom.current().nextInt(1,23);
    minute = ThreadLocalRandom.current().nextInt(1,59);
    
    if (hour < 10) hourString = "0" + hour;
      else hourString = Integer.toString(hour);

    if (month < 10) monthString = "0" + month;
      else monthString = Integer.toString(month);

    if (day < 10) dayString = "0" + day;
      else dayString = Integer.toString(day);

    if (minute < 10) minuteString = "0" + minute;
      else minuteString = Integer.toString(minute);    

    timeStamp = ("\"2001-" + monthString + "-" + dayString + "T" + hourString + ":" + minuteString + ":00-08:00\"");
    return timeStamp;
  }

  public int getNumberOfEmails(){
    return this.emailCount;
  }
}