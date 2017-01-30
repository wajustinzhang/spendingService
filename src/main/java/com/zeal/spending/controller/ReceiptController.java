package com.zeal.spending.controller;

import com.zeal.spending.model.ReceiptEntity;
import com.zeal.spending.repository.ReceiptDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchun-imac on 1/29/17.
 */
@RestController
@Path("/leaz/receipts")
public class ReceiptController {
    @Inject
    ReceiptDAO receiptDAO;

    @RequestMapping(value="/", method = RequestMethod.GET)
//    @ExceptionHandler({ServiceExecption.class})
    public DeferredResult<List<ReceiptEntity>> getReceipts(@RequestParam("token") String autoToken, @RequestParam("uuid")String userUUID) {
        List<ReceiptEntity> receiptEntityList = new ArrayList<>();
        DeferredResult<List<ReceiptEntity>> deferredResult = new DeferredResult<>();

        return deferredResult;
    }

    @GET
    @Path("/{uuid}")
    public ReceiptEntity getReceipt(@PathParam("uuid") String uuid) {
        ReceiptEntity receiptEntity = receiptDAO.get(uuid);

        if(receiptEntity == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return receiptEntity;
    }
}
