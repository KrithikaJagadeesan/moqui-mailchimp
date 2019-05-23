package krithika.mailchimp

import com.ecwid.maleorang.MailchimpClient;
import com.ecwid.maleorang.MailchimpObject;
import com.ecwid.maleorang.method.v3_0.lists.members.EditMemberMethod;
import com.ecwid.maleorang.method.v3_0.lists.members.MemberInfo
import org.moqui.context.ExecutionContext;

class Mailchimp {


     static Map<String, Object> postMailchimp( ExecutionContext ec) {
         String listId="39d935c6b1"
         String apiKey= "3878707fba3d3f59de2ed25f238a5c0f-us20"
           def context= ec.context
            def firstName= context.firstName
            def lastName=context.lastName
            String emailAddress=context.emailAddress


            MailchimpClient client = new MailchimpClient(apiKey)
            try {
                EditMemberMethod.CreateOrUpdate method = new EditMemberMethod.CreateOrUpdate(listId, emailAddress)
                method.status = "subscribed"
                method.merge_fields = new MailchimpObject()
                method.merge_fields.mapping.put("emailAddress",emailAddress )
                method.merge_fields.mapping.put("FNAME",firstName )
                method.merge_fields.mapping.put("LNAME", lastName)

                MemberInfo member = client.execute(method)

            } finally {
                client.close()
            }

         return [:]
    }
}

