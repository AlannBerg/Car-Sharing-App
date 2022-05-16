# Car-Sharing-App


Backend do strony internetowej obsługującej wypożyczalnie samochodową, składa się z 9 endpointów podzielonych dostępem na 3 grupy.

# Dostępne dla niezalogowanego użytkowkika : 

[/cars/getCars]

1. Wyświetlanie dostępnych samochodów, z użyciem filtrów :

![image](https://user-images.githubusercontent.com/76206945/154499578-0f9177f7-dff4-46a3-b695-a8393d8fb9b0.png);

  Dostepne filtry : 
  
  ![image](https://user-images.githubusercontent.com/76206945/154503324-629c912b-cf22-4385-b6ee-dff9e5317dcb.png)


[/localization/getLocalizations]

2. Wyświetlanie dostępnych oddziałów firmy.

![image](https://user-images.githubusercontent.com/76206945/154500127-07c3e6a2-8d3a-4080-88d4-4f47d79c2d54.png);


[/client/register]

3. Rejestracja nowego użyktownika : 

Przyjmuje Jsona w postaci : 

![image](https://user-images.githubusercontent.com/76206945/154502779-688f8779-b8f7-49a1-8ac6-df287cb94257.png)

![image](https://user-images.githubusercontent.com/76206945/154505197-7c37f313-c657-470c-8805-107d07f2cbeb.png)


# Dostępne dla zalogowanego użytkownika : 

[/booking/bookCar]

4. Wypożyczenie samochodu, przykładowe wymagane parametry : 

![image](https://user-images.githubusercontent.com/76206945/154503083-4df76d9a-e042-4779-b48f-61d6aa6eb0bd.png)

![image](https://user-images.githubusercontent.com/76206945/154506115-c2fa7b91-9ae8-40e8-92bb-dc88ecd61362.png)

[/booking/return]

5. Zwrot wypożyczonego auta. Wymagane parametry : 

![image](https://user-images.githubusercontent.com/76206945/154504981-e39640c3-b99e-4e32-b67a-2236d84aeffb.png)

![image](https://user-images.githubusercontent.com/76206945/154506419-cc07b551-16fc-4cf1-aae8-05130b6b8201.png)

[/booking/getbookings]

6. Wyświetlanie wszystkich aktualnie wynajętych aut dla danego klienta. 

![image](https://user-images.githubusercontent.com/76206945/154514597-b7f68045-d722-4e03-9e00-6949b1419595.png)

# Dostępne dla moderatora/admia

[/cars/add]

7. Dodawanie nowego auta do wypożyczalni, przyjmuje Jsona w formie:

![image](https://user-images.githubusercontent.com/76206945/154516630-7a371296-897b-4365-a87f-b9ac100d94d7.png)

![image](https://user-images.githubusercontent.com/76206945/154516873-7b70db09-97c2-4243-b5a9-209ce25395d3.png)

[/cars/delete]

8. Usuwanie auta. Przyjmuje parametr id auta.

![image](https://user-images.githubusercontent.com/76206945/154517256-ecca90b5-1506-4c60-98b5-f04bf315a806.png)

[/client/get]

9. Informacje na temat konta klienta, parametr id klienta: 

![image](https://user-images.githubusercontent.com/76206945/154553065-ee6dcef5-b060-4815-9974-2cb2e6385f0a.png)

