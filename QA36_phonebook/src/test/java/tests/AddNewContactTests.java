package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("johhnytherebel@gmail.com").withPassword("Vovka1234$"));
        }
    }
    @Test
    public void addNewContactSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Taras"+i)
                .lastName("Kaufer")
                .phone("0416658"+i)
                .email("tarik"+i+"@gmail.com")
                .address("Odessa")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(1000);
        app.getHelperContact().fillContactForm(contact);
        pause(1000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact));

    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Taras"+i)
                .lastName("Kaufer")
                .phone("0416658"+i)
                .email("tarik"+i+"@gmail.com")
                .address("Odessa")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        pause(1000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact));
    }
    @Test
    public void addNewContactWrongEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Taras"+i)
                .lastName("Kaufer")
                .phone("0416658"+i)
                .email("tarik"+i+"gmail.com")
                .address("Odessa")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(1000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }



     @Test
    public void addNewContactWrongName(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("")
                .lastName("Kaufer")
                .phone("0416658"+i)
                .email("tarik"+i+"@gmail.com")
                .address("Odessa")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }

    @Test
    public void addNewContactWrongLastName(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Taras"+i)
                .lastName(" ")
                .phone("0416658"+i)
                .email("tarik"+i+"@gmail.com")
                .address("Odessa")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }

    @Test
    public void addNewContactAddressWrong(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Taras"+i)
                .lastName("Kaufer")
                .phone("0416658"+i)
                .email("tarik"+i+"@gmail.com")
                .address("")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(1000);
        app.getHelperContact().fillContactForm(contact);
        pause(1000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());

    }

    @Test
    public void addNewContactPhoneWrong(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Taras"+i)
                .lastName("Kaufer")
                .phone("04@16658"+i)
                .email("tarik"+i+"@gmail.com")
                .address("Odessa")
                .description("friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(1000);
        app.getHelperContact().fillContactForm(contact);
        pause(1000);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());


    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}