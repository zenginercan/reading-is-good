/*
package com.getir.readingisgood.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.getir.readingisgood.service.CustomerService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestCustomerController {

    @InjectMocks
    CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private String productPageDeeplink;

    private String productPageWebURL;

    @Test
    public void testToWebURL() throws Exception {
        Mockito.when(convertService.convertToWebURL(productPageDeeplink)).thenReturn(productPageWebURL);
        ResponseEntity response = converterController.toWebURL(productPageDeeplink);
        String webURL = (String)response.getBody();
        Assert.assertTrue(webURL.contains("www.trendyol.com"));
    }

    @Test
    public void testToDeeplink() throws Exception {
        Mockito.when(convertService.convertToDeeplink(productPageWebURL)).thenReturn(productPageDeeplink);
        ResponseEntity response = converterController.toDeeplink(productPageWebURL);
        String deeplink = (String)response.getBody();
        Assert.assertTrue(deeplink.contains("ty://?Page"));
    }

    @Before
    public void setVariables(){
        productPageDeeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
        productPageWebURL = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064";
        ProductDetailPage linkProductPage = new ProductDetailPage();
        linkProductPage.setLinkTypeEnum(LinkTypeEnum.DEEP_LINK);
        linkProductPage.setLink(productPageDeeplink);
        productDetailPageDeeplink = linkProductPage;
        ProductDetailPage urlProductPage = new ProductDetailPage();
        urlProductPage.setLinkTypeEnum(LinkTypeEnum.WEB_URL);
        urlProductPage.setLink(productPageWebURL);
        productDetailPageWebURL = urlProductPage;

    }

}*/
