/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE190_Integer_Overflow__random_square_42.java
Label Definition File: CWE190_Integer_Overflow.label.xml
Template File: sources-sinks-42.tmpl.java
*/
/*
 * @description
 * CWE: 190 Integer Overflow
 * BadSource: random Set data to a random value
 * GoodSource: A hardcoded non-zero, non-min, non-max, even number
 * Sinks: square
 *    GoodSink: Ensure there will not be an overflow before performing the squaring operation
 *    BadSink : Unchecked squaring operation, which can lead to overflow
 * Flow Variant: 42 Data flow: data returned from one method to another in the same class
 *
 * */

package testcases.CWE190_Integer_Overflow;

import testcasesupport.*;

import java.sql.*;
import javax.servlet.http.*;

import java.security.SecureRandom;
import java.util.logging.Logger;

import java.lang.Math;

public class CWE190_Integer_Overflow__random_square_42 extends AbstractTestCase
{

    private int bad_source() throws Throwable
    {
        int data;

        Logger log_bad = Logger.getLogger("local-logger");

        SecureRandom r = new SecureRandom();
        data = r.nextInt();

        return data;
    }

    public void bad() throws Throwable
    {
        int data = bad_source();

        /* POTENTIAL FLAW: if (data*data) > MAX_VALUE, this will overflow */
        int result = (data * data);

        IO.writeLine("result: " + result);

    }

    /* goodG2B() - use goodsource and badsink */
    private int goodG2B_source() throws Throwable
    {
        int data;

        java.util.logging.Logger log_good = java.util.logging.Logger.getLogger("local-logger");

        /* FIX: Use a hardcoded number that won't cause underflow, overflow,
                divide by zero, or loss-of-precision issues */
        data = 2;

        return data;
    }

    private void goodG2B() throws Throwable
    {
        int data = goodG2B_source();

        /* POTENTIAL FLAW: if (data*data) > MAX_VALUE, this will overflow */
        int result = (data * data);

        IO.writeLine("result: " + result);

    }

    /* goodB2G() - use badsource and goodsink */
    private int goodB2G_source() throws Throwable
    {
        int data;

        Logger log_bad = Logger.getLogger("local-logger");

        SecureRandom r = new SecureRandom();
        data = r.nextInt();

        return data;
    }

    private void goodB2G() throws Throwable
    {
        int data = goodB2G_source();

        int result = 0;

        /* FIX: Add a check to prevent an overflow from occurring
         * NOTE: Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE so we must ensure the random value in
         *       data is not equal to Integer.MIN_VALUE */
        if ((Math.abs(data) != Integer.MIN_VALUE) && (Math.abs(data) <= (int)Math.sqrt(Integer.MAX_VALUE)))
        {
            result = (data * data);
            IO.writeLine("result: " + result);
        }
        else {
            IO.writeLine("Input value is too large to perform squaring.");
        }

    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }

}
