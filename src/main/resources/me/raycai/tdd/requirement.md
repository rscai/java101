
# Requirement

## Executive Summary

System **A** output **Request** files daily, and system **B** want to consume these **Request** files.
But system **B** is incapable of parse **Request** files directly, because it is not supporting the format of **Request**.
Instead, system **B** only supports format of **Security** files.
Fortunately, even though the formats of **Request** file and **Security** file are definitely different,
but the logic content of them are similar.
In other words, it's possible to define a set of rules, to transform **Request** to **Security**.

**Therefore, please create a component to transform **Request** file to **Security** file.**

# File Format
## Driver File Format
**Request** file is pure text file which in *ASCII* encoding. The **Request** file consists of three parts:

1. **Header** is optional. It's at most one line. It starts with "#", and only could be located on first line of **Request** file if has.
2. **Body** is optional. It could contains multiple lines. Each line consists of one or more columns, each column is fixed length.
For example:

```
00001    120    009    B
000234   210    008    C
0921     2134   12     OC
```

3. **Footer** is optional. It's at most one line. It starts with "#", and only could be located on last line of **Request** file if has.
## SOI File Format

**Security** file is pure text file which in *ASCII* encoding. The **Security** file consists of three parts:
1. **Header** is optional. It's at most one line. It starts with "#", and only could be located on first line of **Security** file.
2. **Body** is optional. It could contains multiple lines. Each line consists of one or more columns, columns are seperated by "|".
For example:
```
ISN|3212234|NAM|BID
CSP|4232345|APAC|CLOSE
ISIN|3212344|EMER|OPEN_CLOSE
```
3. **Footer** is optional. It's at most one line. It starts with "#", and only could be located on last line of **Security** if has.

