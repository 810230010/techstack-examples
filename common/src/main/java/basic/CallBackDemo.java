package basic;

public class CallBackDemo {
    public static void main(String[] args) {
        Phone phone = new Phone().addPhoneListener(new PhoneListener() {
            @Override
            public void onAccecpt(Phone phone1) {
                System.out.println("打来电话...");
            }
        });
        phone.waitPhoneCall();
    }
}

interface PhoneListener{
    void onAccecpt(Phone phone);
}

class Phone{
    private PhoneListener phoneListener;

    public Phone addPhoneListener(PhoneListener phoneListener){
        this.phoneListener = phoneListener;
        return this;
    }
    public PhoneListener getPhoneListener() {
        return phoneListener;
    }

    public void setPhoneListener(PhoneListener phoneListener) {
        this.phoneListener = phoneListener;
    }
    public void waitPhoneCall(){
        phoneListener.onAccecpt(Phone.this);
    }
}