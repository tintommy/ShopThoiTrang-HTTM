import pandas as pd
import numpy as np
import sklearn
import sys
from sklearn.decomposition import TruncatedSVD
from sqlalchemy import create_engine
import numpy as np

# Thay đổi các thông số kết nối theo cơ sở dữ liệu SQL Server của bạn
server = 'MSI'
database = 'shopThoiTrang'
username = 'sa'
password = 'sa'

# Tạo một URI kết nối sử dụng SQLAlchemy
connection_string = f"mssql+pyodbc://{username}:{password}@{server}/{database}?driver=SQL+Server"

# Tạo một kết nối đến cơ sở dữ liệu
engine = create_engine(connection_string)

table_name = 'DANHGIA'

# Chỉ định cột quan tâm
columns_to_export = ['MAND', 'MASP', 'SOSAO']

# Sử dụng pandas để đọc dữ liệu từ bảng và chỉ lấy các cột bạn quan tâm
sql_query = f'SELECT {", ".join(columns_to_export)} FROM {table_name}'
df = pd.read_sql(sql_query, engine)

# Ghi dữ liệu vào tệp CSV với tất cả 3 cột
#csv_filename = 'data.csv'
#df.to_csv(csv_filename, header=True, index=False, float_format='%.1f')

ratings_utility_matrix = df.pivot_table(values='SOSAO', index='MAND', columns='MASP', fill_value=0)
# print(ratings_utility_matrix.head())

X = ratings_utility_matrix.T

SVD = TruncatedSVD(n_components=10)
decomposed_matrix = SVD.fit_transform(X)

correlation_matrix = np.corrcoef(decomposed_matrix)



# Tạo ma trận tương quan
correlation_matrix = np.corrcoef(decomposed_matrix)

# Lưu ma trận tương quan vào một tệp sử dụng np.save
np.save('correlation_matrix.npy', correlation_matrix)



import pandas as pd
import numpy as np
import sklearn
import sys
from sklearn.decomposition import TruncatedSVD
from sqlalchemy import create_engine
import numpy as np

correlation_matrix = np.load('correlation_matrix.npy')
# Đầu vào là một chuỗi danh sách sản phẩm, ví dụ: "[sp12_S, sp01_S]"
input_product_str = sys.argv[1]
print(input_product_str)

# Loại bỏ dấu ngoặc vuông và dấu cách trong chuỗi
cleaned_str = input_product_str.replace("[", "").replace("]", "").replace(" ", "")

# Tách chuỗi thành danh sách các mã sản phẩm
product_list = cleaned_str.split(",")

# Duyệt qua danh sách sản phẩm đầu vào và đề xuất cho từng sản phẩm
for product_name in product_list:
    product_names = list(X.index)
    if product_name in product_names:
        product_ID = product_names.index(product_name)
        correlation_product_ID = correlation_matrix[product_ID]

        Recommend = list(X.index[correlation_product_ID > 0.9])

        # Loại bỏ sản phẩm đã mua
        Recommend.remove(product_name)

        # In danh sách sản phẩm đề xuất cho mỗi sản phẩm đầu vào
        for recommended_product in Recommend:
            print("name:", recommended_product)
