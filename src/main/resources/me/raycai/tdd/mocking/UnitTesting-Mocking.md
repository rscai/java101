class: center, middle

## Unit Testing: Mocking
> Mocking by help of **Mockito**

> by **Ray Cai**

> January 16th, 2016

---


## Agenda

1. Why mocking
2. How mocking

---

## Why Mocking

**Unit test is isolated**

**Unit test is repeatable**

**Unit test is automatic**

---

## Example
![Alt text](http://g.gravizo.com/g?
@startuml;
actor requester;
participant transformer;
participant column1MappingDao;
requester -> transformer: transform;
activate transformer;
transformer -> column1MappingDao: map%28inputCode%29;
activate column1MappingDao;
column1MappingDao --> transformer: outputCode;
deactivate column1MappingDao;
transformer --> requester: void;
deactivate transformer;
@enduml
)


---

## Example
![Alt text](http://g.gravizo.com/g?
@startuml;
actor requester;
participant transformer;
participant column1MappingDao %23Gray;
requester -> transformer: transform;
activate transformer;
transformer -> column1MappingDao: map%28inputCode%29;
activate column1MappingDao %23Gray;
column1MappingDao --> transformer: outputCode;
deactivate column1MappingDao;
transformer --> requester: void;
deactivate transformer;
@enduml
)

---

## Mock Dependency for Isolation 
![Alt text](http://g.gravizo.com/g?
@startuml;
actor requester;
participant transformer;
participant mockColumn1MappingDao %23Gray;
requester -> transformer: transform;
activate transformer;
transformer -> mockColumn1MappingDao: map%28inputCode%29;
activate mockColumn1MappingDao %23Gray;
mockColumn1MappingDao --> transformer: outputCode;
deactivate mockColumn1MappingDao;
transformer --> requester: void;
deactivate transformer;
@enduml
)

---

## Fix Changes for Repeat
**Hard code response**
```{java}
import static org.mockito.Mockito.*;

Column1MappingDao mockDao = mock(Column1MappingDao.class);
when(mockDao.map(anyString()).thenReturn("outputCode1");
```

---

## Why Mocking

**Make unit test isolated**

**Make unit test repeatable**

---

## Just Another Example
![Alt text](http://g.gravizo.com/g?
@startuml;
actor requester;
participant transformer;
participant fileWriter %23Gray;
requester -> transformer: transform;
activate transformer;
transformer -> fileWriter: write;
activate fileWriter %23Gray;
fileWriter --> transformer: void;
deactivate fileWriter;
transformer --> requester: void;
deactivate transformer;
@enduml
)

---

## Mock as Event Recorder

```{java}
import static org.mockito.Mockito.*;

FileWriter mockFileWriter = mock(FileWriter.class);

mockFileWriter.write();

verify(mockFileWriter,times(1)).write();
```

---

## Why Mocking

**Make unit test isolated**

**Make unit tst repeatable**

**Make unit test automatic**

---

class: center, middle
## How Mocking
**Mockito**

> Mockito is a mocking framework that tastes really good. 
> It lets you write beautiful tests with clean & simple API. 
> Mockito doesn’t give you hangover because the tests are very readable and they produce clean verification errors.
 
[http://mockito.org](http://mockito.org)

---

## Stubbing Method's Return Value
```{java}
ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);
        
when(mockColumnMappingDao.map(anyString())).thenReturn("ADD");
testObject.setColumnMappingDao(mockColumnMappingDao);
        
FileWriter mockFileWriter = mock(FileWriter.class);

testObject.setFileWriter(mockFileWriter);
        
final String input = "A";
final String expectedOutput = "ADD";
        
final String actualOutput = testObject.transform(input);
        
assertThat(actualOutput,is(expectedOutput));
```

---

## Stubbing with a Customer Answer
```{java}
ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);
final Map<String,String> codeMapping = new HashMap<>();
codeMapping.put("A","ADD");
codeMapping.put("123","COMMON-STOCK");
codeMapping.put("O_B","OPEN_BID");
when(mockColumnMappingDao.map(anyString())).thenAnswer(new Answer<String>() {
    @Override
    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
        final String argument =(String) invocationOnMock.getArguments()[0];
        final String mappedCode = codeMapping.get(argument);
                
        return mappedCode;
    }
});
        
testObject.setColumnMappingDao(mockColumnMappingDao);
        
final FileWriter mockFileWriter = mock(FileWriter.class);
testObject.setFileWriter(mockFileWriter);
        
final String input = "A|123|O_B";
final String expectedOutput = "ADD|COMMON-STOCK|OPEN_BID";
final String actualOutput = testObject.transform(input);
        
assertThat(actualOutput,is(expectedOutput));
        
```
---

## Verify Behavior
```{java}
ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);
final Map<String,String> codeMapping = new HashMap<>();
codeMapping.put("A","ADD");
codeMapping.put("123","COMMON-STOCK");
codeMapping.put("O_B","OPEN_BID");
when(mockColumnMappingDao.map(anyString())).thenAnswer(new Answer<String>() {
    @Override
    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
        final String argument =(String) invocationOnMock.getArguments()[0];
        final String mappedCode = codeMapping.get(argument);
        return mappedCode;
    }
});
testObject.setColumnMappingDao(mockColumnMappingDao);
final FileWriter mockFileWriter = mock(FileWriter.class);
testObject.setFileWriter(mockFileWriter);
final String input = "A|123|O_B";
final String expectedOutput = "ADD|COMMON-STOCK|OPEN_BID";
final String actualOutput = testObject.transform(input);
assertThat(actualOutput,is(expectedOutput));
// verify behavior
verify(mockFileWriter,times(1)).write(anyString());
```
---

## Verify with Argument Matching
```{java}
ColumnMappingDao mockColumnMappingDao = mock(ColumnMappingDao.class);
final Map<String,String> codeMapping = new HashMap<>();
codeMapping.put("A","ADD");
codeMapping.put("123","COMMON-STOCK");
codeMapping.put("O_B","OPEN_BID");
when(mockColumnMappingDao.map(anyString())).thenAnswer(new Answer<String>() {
    @Override
    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
        final String argument =(String) invocationOnMock.getArguments()[0];
        final String mappedCode = codeMapping.get(argument);
        return mappedCode;
    }
});
testObject.setColumnMappingDao(mockColumnMappingDao);
final FileWriter mockFileWriter = mock(FileWriter.class);
testObject.setFileWriter(mockFileWriter);
final String input = "A|123|O_B";
final String expectedOutput = "ADD|COMMON-STOCK|OPEN_BID";
final String actualOutput = testObject.transform(input);
assertThat(actualOutput,is(expectedOutput));

// verify behavior with argument matching
ArgumentCaptor<String> writeContentCaptor = ArgumentCaptor.forClass(String.class);
verify(mockFileWriter,times(1)).write(writeContentCaptor.capture());
assertThat(writeContentCaptor.getValue(),is(expectedOutput));
```
---

## Reference

* [Mockito: A Simple, Intuitive Mocking Framework, Marcin Zajączkowski](https://dzone.com/refcardz/mockito)

---

class: center, middle
## Q&A

---

class: center, middle
## Thank You

-Good Bye-