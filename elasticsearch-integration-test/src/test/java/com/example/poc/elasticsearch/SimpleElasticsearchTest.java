package com.example.poc.elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Demonstrates how to use an embedded elasticsearch server in your tests.
 *
 * @author rajakolli
 */
public class SimpleElasticsearchTest extends AbstractElasticsearchIntegrationTest
{

    @Test
    public void indexSimpleDocument() throws IOException
    {
        getClient().prepareIndex("myindex", "document", "1")
                .setSource(jsonBuilder().startObject().field("test", "123").endObject())
                .execute().actionGet();

        GetResponse fields = getClient().prepareGet("myindex", "document", "1").execute()
                .actionGet();
        assertThat(fields.getSource().get("test")).isEqualTo("123");
    }
}
