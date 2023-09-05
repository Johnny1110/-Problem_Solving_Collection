# 社交帳號登入資源整理

<br>

---

<br>

關於 social_account 登入，之後有時間我再整理一下，把相關過程，以及文件整理一下。這邊暫時先放一些重要的文件，提供參考。

<br>
<br>

## Google 測試 Google Oauth 2.0 API 的平台

<br>

url: https://developers.google.com/oauthplayground/

<br>

如果是需要用 Oauth 2.0 登入，在第一步 Select & authorize APIs 時，選擇 `Google Oauth2 API v2`，scope 可以選 `email, profile, openid`。

<br>

重要 url 與參數

```
tokenUri: https://oauth2.googleapis.com/token
userInfoUrl: https://www.googleapis.com/oauth2/v2/userinfo
authorizationUri: https://accounts.google.com/o/oauth2/auth
```

<br>
<br>

---

<br>
<br>


## Facebook Scope 與可獲取之 Resource

<br>

scope 列表：https://developers.facebook.com/docs/permissions/reference/public_profile

scope 種類：`email, public_profile`

resource 說明文件：https://developers.facebook.com/docs/graph-api/reference/user/

基本可獲得欄位：
```
id
first_name
last_name
middle_name
name
name_format
picture
short_name
```

<br>

重要 url 與參數
```
authorizationUri: https://www.facebook.com/v3.0/dialog/oauth
tokenUri: https://graph.facebook.com/v3.0/oauth/access_token
userInfoUri: https://graph.facebook.com/v3.0/me?fields=id,email
```

<br>
<br>

---

<br>
<br>

## Discord 的 scope 與 resource

<br>

scope 文件：https://discord.com/developers/docs/topics/oauth2#shared-resources-oauth2-scopes
可獲得欄位文件：https://discord.com/developers/docs/resources/application


使用 scope: email

(discord 文件寫得比較清楚，我就不列出來了)

<br>

重要 url 與參數
```
authorizationUri: https://discord.com/oauth2/authorize
tokenUri: https://discord.com/api/oauth2/token
userInfoUri: https://discord.com/api/users/@me
scope: identify
```

<br>
<br>

---

<br>
<br>

## Apple 的 scope 與 exchange AccessToken

<br>

apple 的 resource server 怎麼訪問，說實話，我找不到，我最多只找到怎麼換 access token，並且回應參數裡面已經有足夠的資訊串接 oauth 做 user 建立了，所以單純做登入其實也沒必要訪問 resource server。

(apple 的文件寫得很爛，這邊我就提供我用的 scope，跟實際拿到的資料)

scope: `openid,name,email`

欄位：

```
iss
發行身份令牌的主體，一般為 https://appleid.apple.com

sub
user 的唯一識別碼 ID

aud
應用的 client ID

iat
發布身份令牌的時間 (Unix time)

exp
身份令牌過期的時間 (Unix time) ps: 這個時間不用特別關心，對於我們的應用，只需要 sub 就可以了，利用 sub 在應用中生成 consumer

nonce
A string for associating a client session with the identity token. This value mitigates replay attacks and is present only if you pass it in the authorization request.

nonce_supported
A Boolean value that indicates whether the transaction is on a nonce-supported platform. If you send a nonce in the authorization request, but don’t see the nonce claim in the identity token, check this claim to determine how to proceed. If this claim returns true, treat nonce as mandatory and fail the transaction; otherwise, you can proceed treating the nonce as optional.

email
user 的 email

email_verified
是否驗證電子郵件
```


<br>

重要 url 與參數

```
clientId: Asabulu
keyId: MP6N597V4B
teamId: Z497N1QDJ0
bundleId: This is Asabulu (Z497N1QDJ0.com.asabulu.aabb)
authorization-grant-type: authorization_code
redirectUri: https://DOMAIN/oauth2/redirect
scope: name,email
authorizationUri: https://appleid.apple.com/auth/authorize?response_mode=form_post
token-uri: https://appleid.apple.com/auth/token
```