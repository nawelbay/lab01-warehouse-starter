# Protocol v1 (Line-based over TCP)

**Framing**: 1 request = 1 line (UTF-8), terminated by `\n`.

## Commands
- `HELLO <clientId>`
- `LIST`
- `GET <item>`
- `ORDER <orderId> <item> <qty>`
- `CANCEL <orderId>`
- `PING`
- `BYE`

## Responses
- `OK <message>`
- `DATA <payload>`
- `ERR <code> <message>`

## Examples
```
C: HELLO storeA
S: OK welcome storeA
C: GET laptop
S: DATA laptop=12
C: ORDER o100 laptop 2
S: OK accepted o100 remaining=10
C: BYE
S: OK bye
```
