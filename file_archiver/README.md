# Job4j_Util_File_archiver
### ��� ��������� ������� ��� ��������� ����� c ����������� ��������� ��������� 


___
### ����������� �������.

1. ��� ������� ����������� �����, ������� �� ����� ������������, ��������: c:\project\job4j\ <br><br>
2. � �������� ������ ���������:
    - ���������� ������, ������� �� ����� �������� � �����;
    - ���� � ��������� zip �����. <br><br>
3. ����� ������ ��������� ��������� �������. �� ���� ��������� ��������. <br><br>
4. ������ �������:

  > -d=c:\project\job4j\&nbsp;&nbsp;-e=.class&nbsp;&nbsp;-o=c:\project\job4j.zip


 - -d {directory} - ���� � ������������ ����� � �������;
 - -e {exclude} - ��������� �� ������������� ����� � ����������� class;
 - -o {output} - ���� � ��������� zip �����.

<br>

___
### ���������� � ���������

- Java 9
- Maven v.3.6.3

<br>

---
### ������ �������

1. ������� ����� ������� � github �� ������ � ��������������� � ��������� ����������:<br>
   [https://github.com/MasterMaxTs/job4j_design_utils/archive](https://github.com/MasterMaxTs/job4j_design_utils/archive/refs/heads/master.zip)


2. ������� � ���������� �������, � ����� <b>file_archiver</b> ������� ��������� ������ <b>�� ����� ��������������</b>.</br>
   - ��� <ins>�������</ins> ������� ������� ��������� ��������������� �������:
       - ```mvn package -Dmaven.test.skip=true```
       - ```java -jar target/file_archiver.jar -d=<SOURCE_FOLDER> -e=<EXCLUDE_FILE_EXTENSION> -o=<TARGET_ZIP_FILE>```

   - ��� <ins>������������</ins> ������� ���������� ��������� �������:
      - ```java -jar target/file_archiver.jar -d=<SOURCE_FOLDER> -e=<EXCLUDE_FILE_EXTENSION> -o=<TARGET_ZIP_FILE>```

<br>

___
### �������������� � ��������
<br>

1. ��� �� �������: ��������� ������ � ������������ �����:<br><br>
   ![img.png](img/input-data.JPG)


2. ��� �� �������: �������� ��������� �����:<br><br>
   ![img.png](img/archive-folder-success.JPG)

   
3. ��� �� �������: �������������� ��������, ��������� � ��������� ���������������� ������ ������:<br>
- �� ������� ��������� ��������� ������:
  <br><br>
    - ![img.png](img/error-missing-key.JPG)


- ������o �������� �������� �����:
  <br><br>
    - ![img.png](img/error-invalid-key(-d)-value.JPG)


- ������o �������� ���������� ��������� ��������� �����:
  <br><br>
    - ![img.png](img/error-incorrect-target-file-extension.JPG)


- �� ������� �������� �����:
  <br><br>
    - ![img.png](img/error-key-without-value.JPG)


- ������o �������� �������� �����:
  <br><br>
    - ![img.png](img/error-invalid-key-name.JPG)


- ������� ����� � ���������� ���������:
  <br><br>
    - ![img.png](img/error-find-duplicate-keys.JPG)

___
### ��������
* Email: java.dev-maxim.tsurkanov@yandex.ru
* Skype: https://join.skype.com/invite/ODADx0IJ3BBu
* VK: https://m.vk.com/id349328153
* Telegram: matsurkanov