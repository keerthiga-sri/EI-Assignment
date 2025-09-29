interface Notification {
    void notifyUser(String message);
}

class EmailNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("Email: " + message);
    }
}

class SMSNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("SMS: " + message);
    }
}

class PushNotification implements Notification {
    public void notifyUser(String message) {
        System.out.println("Push: " + message);
    }
}

class NotificationFactory {
    public static Notification getNotification(String type) {
        switch (type.toLowerCase()) {
            case "email": return new EmailNotification();
            case "sms": return new SMSNotification();
            case "push": return new PushNotification();
            default: return null;
        }
    }
}

// Client
public class FactoryNotification {
    public static void main(String[] args) {
        Notification n1 = NotificationFactory.getNotification("email");
        n1.notifyUser("Welcome to our service!");

        Notification n2 = NotificationFactory.getNotification("sms");
        n2.notifyUser("Your OTP is 12345.");

        Notification n3 = NotificationFactory.getNotification("push");
        n3.notifyUser("You have a new message.");
    }
}
