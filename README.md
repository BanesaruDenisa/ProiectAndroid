
# Proiect Testare Aplicație Android
## Introducere
Testele automate realizate în cadrul acestui proiect sunt destinate unei aplicații Android dezvoltate pentru disciplina "Programare pe Dispozitive Mobile" din anul III.
Această aplicație este concepută pentru a ajuta utilizatorii să-și gestioneze și să-și țină evidența propriilor cheltuieli. Testele sunt scrise folosind framework-urile Espresso și UiAutomator pentru testarea interfeței de utilizator.
Aceste teste acoperă diverse funcționalități, cum ar fi înregistrarea, încărcarea și ștergerea acțiunilor din aplicație.

## Diferența între Espresso și UiAutomator 
**Espresso** 
- Este potrivit pentru testarea detaliată a interacțiunilor la nivel de ecran sau fragment și verificarea stării interfeței de utilizator.
- Utilizăm metode specifice pentru a identifica și verifica starea elementelor interfeței de utilizator, cum ar fi onView() și check(matches()).
**UIAutomator**
- Potrivit pentru testarea interacțiunilor între mai multe aplicații și componente ale sistemului de operare Android.
- Utilizăm obiecte UiObject și metode UiSelector pentru a găsi și verifica starea elementelor interfeței de utilizator, cum ar fi findObject() și exists()


## Funcționalitățile testate 
**Delete**
Testul de ștergere verifică dacă funcționalitatea de ștergere a unui element din aplicație funcționează corect. Acesta implică simularea acțiunii de selectare a unui element și apoi de ștergere a acestuia, urmată de verificarea rezultatului.
În cazul unui test reușit, se asigură că elementul a fost șters și că interfața utilizatorului s-a actualizat. 

**Upload**
Testul de încărcare verifică funcționalitatea de încărcare a unei noi entități sau informații în aplicație. Acesta implică completarea diferitelor câmpuri de încărcare, cum ar fi titlul, descrierea, bugetul etc., urmate de trimiterea acestor date către aplicație.În cazul unui test reușit, se asigură că datele au fost trimise corect și că entitatea sau informația nouă este afișată în mod corespunzător în aplicație.

**SingUp**
Testul de înregistrare verifică funcționalitatea procesului de înregistrare a unui nou utilizator în aplicație. Acesta implică introducerea datelor necesare pentru înregistrare, cum ar fi adresa de email și parola, urmată de trimiterea acestor date către aplicație.

## Motivație 
Am  ales să facem aceste teste deoarece sunt esențiale pentru asigurarea calității aplicației, garantând că principalele functionalități: înregistrarea utilizatorului, încărcarea datelor dar și ștergerea corepund aștepărilor utilizatorilor. 

## Raport AI 
În urma comparației cu tool-ul ChatGPT am observat că în testarea software poate aduce multiple beneficii deoarece respectă structura framework-urilor și oferă indicații pentru rezolvarea erorilor dar nu oferă o soluție completă. Testele generate de AI nu au trecut din prima, deoarece este nevoie de o cunoaștere foarte detaliată a aplicației și a structurii acesteia pentru a putea crea testele adecvate. Concluzia noastră este că, o abordare hibridă care integrează atât testele autogenerate, cât și cele manuale ofera o bună acoperire și o asigurare a calității în testarea software. 

## Link-uri 

