#Answers to Theoretical Questions

## EF
**How are references (foreign keys) defined?**

Using the **HasForeignKey** Method like found in the given Sourcecode:
```C#
var person = modelBuilder.Entity<Person>();

// Add Primary Key
person.HasKey(p => p.SSN);

// Add Foreign Key
person.HasMany(p => p.Addresses).WithOne(a => a.Person).HasForeignKey(a => a.SSN);
```

**How can you add objects (including dependent child objects)?**

Objects can be simply added after Creating a **Database Context** and calling the method **AddAsync** to asynchronously add an entity.

```C#
await context.Person.AddAsync(newPerson);
```

**How are changes committed?**

By Calling the Method
```C#
await context.SaveChangesAsync();
```
**How can referenced tables be used in queries (resulting in joins)?**

The **.Include** Method can be used to Load related/referenced Entities


**How are the usual SQL clauses like SELECT, WHERE, GROUP BY etc. used?**

In C# the Queries are depicted using Linq. The given Linq-Statement will be converted to plain SQL which is then sent to the DBMS.

**Try to find out: which parts of the LINQ query are executed on the database (= translated to SQL) and which are done in memory?**

Firstly, how getting the data is done totally depends on the configuration of the DatabaseContext.

Grouping, Sorting and obviously selecting the values into different Collections is not done on the Database.

## JPA 1
**Take a look at the Wealth column: we never did define a value for our two new students, yet EF inserted 0.0 while JPA inserted null - why?**

Because in C# **decimal** is a **ValueType** and therefore the value can't be *null* whereas the **BigDecimal** in JAVA is a **Object** and followingly will carry the value *null* if not initialized.

**Why did we have to change the awesomeness from double to Double in the Person class?**

So that *null* can be Inserted into the Person-Table.