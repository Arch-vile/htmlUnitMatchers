package com.moonillusions.htmlUnitMatchers.matchers;

import static com.moonillusions.htmlUnitMatchers.matchers.HasAttribute.hasAttribute;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.moonillusions.htmlUnitMatchers.TestUtils;

public class HasAttributeTest {

    @Test
    public void matchesSingleAttribute() throws FailingHttpStatusCodeException,
            MalformedURLException, IOException {
        assertThat(TestUtils.createDomNode("<span attr1=1>text</span>"),
                hasAttribute("attr1", "1"));
    }

    @Test
    public void matchesSingleAttributeWithDoubleQuotes()
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {
        assertThat(TestUtils.createDomNode("<span attr1=\"1\">text</span>"),
                hasAttribute("attr1", "1"));
    }

    @Test
    public void matchesFromMultipleAttributes()
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {
        assertThat(
                TestUtils.createDomNode("<span attr1=1 attr2=2>text</span>"),
                allOf(hasAttribute("attr1", "1"), hasAttribute("attr2", "2")));
    }

    @Test
    public void matchesNoValueAttributes() throws IOException {
        assertThat(TestUtils.createDomNode("<span novalue>text</span>"),
                hasAttribute("novalue", ""));
    }

    @Test
    public void matchesDuplicateAttribute() throws IOException {
        assertThat(
                TestUtils.createDomNode("<span attr1=1 attr1=2>text</span>"),
                allOf(hasAttribute("attr1", "1"),
                        not(hasAttribute("attr1", "2"))));
    }

    @Test
    public void failsIfAttributeValueMismatch()
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {
        assertThat(
                TestUtils.createDomNode("<span attr1=1 attr2=2>text</span>"),
                not(hasAttribute("attr1", "some")));
    }

    @Test
    public void failsIfNoAttributes() throws FailingHttpStatusCodeException,
            MalformedURLException, IOException {
        assertThat(TestUtils.createDomNode("<span>text</span>"),
                not(hasAttribute("attr1", "1")));
    }

    @Test
    public void verifyDescription() {
        HasAttribute test = hasAttribute("attr1", "1");
        TestUtils.assertDescribeTo(test,
                "Has attribute name: [attr1] value: [1]");
    }

    @Test
    public void verifyMismatchDescriptionAttributeNotFound()
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {
        HtmlElement span = TestUtils.createDomNode("<span attr1=1>text</span>");
        HasAttribute test = hasAttribute("age", "1");
        TestUtils
                .assertDescribeMismatch(
                        test,
                        span,
                        "On HtmlSpan[<span attr1=\"1\">] did not have expected attribute name: [age] value: [1]");
    }

    @Test
    public void verifyMismatchDescriptionAttributeValue()
            throws FailingHttpStatusCodeException, MalformedURLException,
            IOException {
        HtmlElement span = TestUtils.createDomNode("<span attr1=1>text</span>");
        HasAttribute test = hasAttribute("attr1", "2");
        TestUtils
                .assertDescribeMismatch(
                        test,
                        span,
                        "On HtmlSpan[<span attr1=\"1\">] on attribute DomAttr[name=attr1 value=1] did not match expected value: [2]");
    }

}
