namespace java com.github.rudysysu.codes.thriftclient
namespace cpp com.github.rudysysu.codes.thriftclient
namespace py thriftclient

exception TServiceException {
  1: i32 code;
  2: string message;
  3: string expand;
}

struct TBizRiskControlUserConsumeStat {
  1: i64 uid;
  2: i64 totalAmount;
  3: i64 totalCount;
  4: i64 latestAmount;
  5: i64 latestCount;
}

service TBizRiskControlStatService {
  /**
   * 查询用户消费统计
   * 累计数据从2023-03-01开始
   * 最近数据从2024-02-01开始
   * @param uid 用户UID，必填
   * @param latestNDays 查最近N天的数据，必填
   * @return BizRiskControlUserConsumeStat
   * @throws ServiceException
   */
  TBizRiskControlUserConsumeStat queryUserConsumeStat(1: i64 uid, 2: i32 latestNDays) throws (1: TServiceException ex1);
}
