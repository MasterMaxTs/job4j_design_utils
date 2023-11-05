# Job4j_Util_Duplicate_files_finder
### ��� ��������� ������� ��� ������ ������-����������


___
### ����������� �������.

1. ��� ������� ����������� �����, � ������� ����� �������� ����� ������-����������, �������� c:\project\job4j\ <br><br>

2. ������ �������:

> -d=c:\project\job4j\&nbsp;&nbsp;

- -d {directory} : ���� � �����, � ������� ����� ����������� ����� ������-����������.

3. ��������� ������ � ���� ������ ������ ��������� � �������.


4. ���� ����� ��������������: 
   - ������;
   - ���;
   - ���������� ���� � ������������.

<br>

___
### ���������� � ���������

- Java 11
- Maven v.3.6.3

<br>

---
### ������ �������

1. ������� ����� ������� � github �� ������ � ��������������� � ��������� ����������:<br>
   [https://github.com/MasterMaxTs/job4j_design_utils/archive](https://github.com/MasterMaxTs/job4j_design_utils/archive/refs/heads/master.zip)


2. ������� � ���������� �������, � ����� <b>duplicate_files_finder</b> ������� ��������� ������.</br>
    - ��� <ins>�������</ins> ������� ������� ��������� ��������������� �������:
        - ```mvn package -Dmaven.test.skip=true```
        - ```java -jar target/duplicate_files_finder.jar -d=<SOURCE_FOLDER>```

    - ��� <ins>������������</ins> ������� ���������� ��������� �������:
        - ```java -jar target/duplicate_files_finder.jar -d=<SOURCE_FOLDER>```

<br>

___
### �������������� � ��������
<br>

1. ��� �� �������: ��������� ������ � ����� ������:<br><br>
   ![img.png](img/input-data.JPG)


2. ��� �� �������: ����� ������-����������:<br><br>
   ![img.png](img/find-duplicate-files-success.JPG)



3. ��� �� �������: �������������� ��������, ��������� � ��������� ���������������� ������ ������:
- �� ������� ��������� ��������� ������:
  <br><br>
    - ![img.png](img/error-missing-key.JPG)


- ������o �������� �������� ����� �������� ����������:
  <br><br>
    - ![img.png](img/error-invalid-key(-d)-value.JPG)


- ������o �������� ��� �����:
  <br><br>
    - ![img.png](img/error-invalid-key-name.JPG)


- �� ������� �������� �����:
  <br><br>
    - ![img.png](img/error-key-without-value.JPG)

    
___
### ��������
* Email: java.dev-maxim.tsurkanov@yandex.ru
* Skype: https://join.skype.com/invite/ODADx0IJ3BBu
* VK: https://m.vk.com/id349328153
* Telegram: matsurkanov