import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Container,
  Paper,
  TextField,
  Button,
  Grid,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Chip,
  Card,
  CardContent,
  Box,
  Collapse,
  MenuItem,
  Select,
  FormControl,
  InputAdornment,
  Avatar
} from "@mui/material";
import {
  Search,
  LocationOn,
  AttachMoney,
  DateRange,
  People,
  Computer,
  Business,
  FilterList,
  Clear,
  Bolt
} from "@mui/icons-material";

export default function App() {
  /* ───────── Dummy Data ───────── */
  const [rows] = useState([
    {
      name: "【建設系】プロジェクトマネジメント支援案件のご案内 【キャリアビート 熊谷：39487885188】", min: 60, max: 70, location: "東京都",
      rate: 5, period: "202507", skills: ["C#", "Java", "MySQL"],
      flow: "2次請け", interview: 1, remote: "ハイブリッド", link: "#"
    },
    {
      name: "【面談設定済み★】java/要件定義/基本設計/常駐/外国籍可/個人事業主可 【キャリアビート 太田：39434935848】", min: 60, max: 90, location: "神奈川県",
      rate: 5, period: "202507", skills: ["AWS", "Linux"],
      flow: "2次請け", interview: 1, remote: "常駐", link: "#"
    },
    {
      name: "C案件", min: 75, max: 95, location: "東京都",
      rate: 4, period: "202508", skills: ["React", "Node.js", "TypeScript"],
      flow: "元請", interview: 2, remote: "フルリモート", link: "#"
    },
    {
      name: "D案件", min: 55, max: 65, location: "埼玉県",
      rate: 5, period: "202507", skills: ["Python", "Django", "PostgreSQL"],
      flow: "2次請け", interview: 1, remote: "ハイブリッド", link: "#"
    },
    {
      name: "E案件", min: 80, max: 100, location: "神奈川県",
      rate: 5, period: "202509", skills: ["Go", "Docker", "Kubernetes"],
      flow: "元請", interview: 1, remote: "フルリモート", link: "#"
    },
    {
      name: "F案件", min: 65, max: 85, location: "東京都",
      rate: 4, period: "202508", skills: ["Vue.js", "Laravel", "MySQL"],
      flow: "2次請け", interview: 2, remote: "常駐", link: "#"
    },
    {
      name: "G案件", min: 70, max: 90, location: "神奈川県",
      rate: 5, period: "202507", skills: ["Flutter", "Firebase", "Dart"],
      flow: "元請", interview: 1, remote: "ハイブリッド", link: "#"
    },
    {
      name: "H案件", min: 50, max: 70, location: "埼玉県",
      rate: 3, period: "202508", skills: ["PHP", "Symfony", "Redis"],
      flow: "2次請け", interview: 2, remote: "常駐", link: "#"
    },
    {
      name: "I案件", min: 85, max: 110, location: "東京都",
      rate: 5, period: "202509", skills: ["Rust", "WebAssembly", "GraphQL"],
      flow: "元請", interview: 1, remote: "フルリモート", link: "#"
    }
  ]);

  /* ───────── Search State ───────── */
  const [filter, setFilter] = useState({
    type: "", name: "", min: "", max: "",
    location: "", rate: "", period: "",
    skill: "", flow: "", interview: "", remote: ""
  });

  const [isFiltersExpanded, setIsFiltersExpanded] = useState(false);

  const handleChange = (key) => (e) =>
    setFilter({ ...filter, [key]: e.target.value });

  const clearFilters = () => {
    setFilter({
      type: "", name: "", min: "", max: "",
      location: "", rate: "", period: "",
      skill: "", flow: "", interview: "", remote: ""
    });
  };

  const getRemoteColor = (remote) => {
    switch (remote) {
      case "フルリモート": return "success";
      case "ハイブリッド": return "info";
      case "常駐": return "warning";
      default: return "default";
    }
  };

  const getFlowColor = (flow) => {
    return flow === "元請" ? "secondary" : "primary";
  };

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#f5f7fa' }}>
      {/* ヘッダー */}
      <AppBar position="sticky" sx={{ 
        background: 'linear-gradient(135deg, #1976d2 0%, #1565c0 100%)',
        boxShadow: '0 4px 20px rgba(25, 118, 210, 0.3)'
      }}>
        <Toolbar>
          <Avatar sx={{ 
            mr: 2, 
            background: 'linear-gradient(135deg, #ff6b6b 0%, #ee5a52 100%)'
          }}>
            <Bolt fontSize="small" sx={{ color: "white" }} />
          </Avatar>
          <Box>
            <Typography variant="h5" component="div" sx={{ fontWeight: 'bold' }}>
              SES Pallet
            </Typography>
            <Typography variant="body2" sx={{ opacity: 0.9 }}>
              AIによるSES営業支援ツール
            </Typography>
          </Box>
        </Toolbar>
      </AppBar>

      <Container maxWidth="xll" sx={{ py: 4 }}>
        {/* 検索セクション */}
        <Paper 
          elevation={3} 
          sx={{ 
            mb: 4, 
            borderRadius: 3,
            background: 'linear-gradient(135deg, #ffffff 0%, #f8f9ff 100%)',
            overflow: 'hidden'
          }}
        >
          {/* メイン検索バー */}
          <Box sx={{ p: 3, borderBottom: '1px solid #e0e0e0' }}>
            <Grid container spacing={2} alignItems="flex-end">
              <Grid item xs={12} md={8}>
                <TextField
                  fullWidth
                  variant="outlined"
                  placeholder="案件名で検索..."
                  size="large"
                  value={filter.name}
                  onChange={handleChange("name")}
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <Search color="action" />
                      </InputAdornment>
                    ),
                  }}
                  sx={{
                    '& .MuiOutlinedInput-root': {
                      borderRadius: 2,
                      backgroundColor: 'white'
                    }
                  }}
                />
              </Grid>
              <Grid item xs={6} md={2}>
                <Button
                  fullWidth
                  variant={isFiltersExpanded ? "contained" : "outlined"}
                  size="large"
                  startIcon={<FilterList />}
                  onClick={() => setIsFiltersExpanded(!isFiltersExpanded)}
                  sx={{ 
                    borderRadius: 2,
                    height: 56
                  }}
                >
                  詳細検索
                </Button>
              </Grid>
              <Grid item xs={6} md={2}>
                <Button
                  fullWidth
                  variant="contained"
                  size="large"
                  sx={{ 
                    borderRadius: 2,
                    height: 56,
                    background: 'linear-gradient(135deg, #1976d2 0%, #1565c0 100%)',
                    fontWeight: 'bold'
                  }}
                >
                  検索
                </Button>
              </Grid>
                            <Grid item xs={6} md={2}>
                <Button
                  fullWidth
                  variant="contained"
                  size="large"
                  sx={{ 
                    borderRadius: 2,
                    height: 56,
                    background: 'linear-gradient(135deg,rgb(97, 212, 68) 0%,rgb(69, 192, 21) 100%)',
                    fontWeight: 'bold'
                  }}
                >
                  自動マッチング
                </Button>
              </Grid>
            </Grid>
          </Box>

          {/* 詳細フィルター */}
          <Collapse in={isFiltersExpanded}>
            <Box sx={{ p: 3, backgroundColor: '#f8f9fa' }}>
              <Grid container spacing={3}>
                {/* 単価範囲 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <AttachMoney fontSize="small" />
                    単価範囲
                  </Typography>
                  <Grid container spacing={1}>
                    <Grid item xs={6}>
                      <TextField
                        fullWidth
                        size="small"
                        type="number"
                        placeholder="下限"
                        value={filter.min}
                        onChange={handleChange("min")}
                      />
                    </Grid>
                    <Grid item xs={6}>
                      <TextField
                        fullWidth
                        size="small"
                        type="number"
                        placeholder="上限"
                        value={filter.max}
                        onChange={handleChange("max")}
                      />
                    </Grid>
                  </Grid>
                </Grid>

                {/* 勤務地 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <LocationOn fontSize="small" />
                    勤務地
                  </Typography>
                  <FormControl fullWidth size="small">
                    <Select
                      value={filter.location}
                      onChange={handleChange("location")}
                      displayEmpty
                    >
                      <MenuItem value="">すべて</MenuItem>
                      <MenuItem value="東京都">東京都</MenuItem>
                      <MenuItem value="神奈川県">神奈川県</MenuItem>
                      <MenuItem value="埼玉県">埼玉県</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>

                {/* 稼働率 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <DateRange fontSize="small" />
                    稼働率
                  </Typography>
                  <TextField
                    fullWidth
                    size="small"
                    type="number"
                    placeholder="稼働率 (%)"
                    value={filter.rate}
                    onChange={handleChange("rate")}
                  />
                </Grid>

                {/* 期間 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <DateRange fontSize="small" />
                    開始期間
                  </Typography>
                  <TextField
                    fullWidth
                    size="small"
                    placeholder="YYYYMM"
                    value={filter.period}
                    onChange={handleChange("period")}
                  />
                </Grid>

                {/* 必須スキル */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <Computer fontSize="small" />
                    必須スキル
                  </Typography>
                  <TextField
                    fullWidth
                    size="small"
                    placeholder="スキルで検索"
                    value={filter.skill}
                    onChange={handleChange("skill")}
                  />
                </Grid>

                {/* 商流 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <Business fontSize="small" />
                    商流
                  </Typography>
                  <FormControl fullWidth size="small">
                    <Select
                      value={filter.flow}
                      onChange={handleChange("flow")}
                      displayEmpty
                    >
                      <MenuItem value="">すべて</MenuItem>
                      <MenuItem value="元請">元請</MenuItem>
                      <MenuItem value="2次請け">2次請け</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>

                {/* 面談回数 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <People fontSize="small" />
                    面談回数
                  </Typography>
                  <TextField
                    fullWidth
                    size="small"
                    type="number"
                    placeholder="面談回数"
                    value={filter.interview}
                    onChange={handleChange("interview")}
                  />
                </Grid>

                {/* リモート可否 */}
                <Grid item xs={12} sm={6} md={3}>
                  <Typography variant="subtitle2" sx={{ mb: 1, fontWeight: 'bold', display: 'flex', alignItems: 'center', gap: 1 }}>
                    <Computer fontSize="small" />
                    リモート可否
                  </Typography>
                  <FormControl fullWidth size="small">
                    <Select
                      value={filter.remote}
                      onChange={handleChange("remote")}
                      displayEmpty
                    >
                      <MenuItem value="">すべて</MenuItem>
                      <MenuItem value="フルリモート">フルリモート</MenuItem>
                      <MenuItem value="ハイブリッド">ハイブリッド</MenuItem>
                      <MenuItem value="常駐">常駐</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>
              </Grid>

              <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mt: 3, pt: 2, borderTop: '1px solid #ddd' }}>
                <Button
                  startIcon={<Clear />}
                  onClick={clearFilters}
                  color="inherit"
                >
                  フィルターをクリア
                </Button>
                <Typography variant="body2" color="text.secondary">
                  {rows.length}件の案件が見つかりました
                </Typography>
              </Box>
            </Box>
          </Collapse>
        </Paper>

        {/* テーブル */}
        <TableContainer 
          component={Paper} 
          elevation={3}
          sx={{ 
            borderRadius: 3,
            background: 'linear-gradient(135deg, #ffffff 0%, #f8f9ff 100%)'
          }}
        >
          <Table sx={{ minWidth: 1280 }}>
            <TableHead>
              <TableRow sx={{ backgroundColor: '#f5f5f5' }}>
                <TableCell sx={{ fontWeight: 'bold' }}>案件名</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>単価</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>勤務地</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>稼働率</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>期間</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>スキル</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>商流</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>面談</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>リモート</TableCell>
                <TableCell sx={{ fontWeight: 'bold' }}>アクション</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row, idx) => (
                <TableRow 
                  key={idx} 
                  sx={{ 
                    '&:hover': { backgroundColor: '#f0f7ff' },
                    transition: 'background-color 0.2s'
                  }}
                >
                  <TableCell>
                    <Typography variant="body1" fontWeight="small">
                      {row.name}
                    </Typography>
                  </TableCell>
                  <TableCell>
                    <Typography variant="body2" fontWeight="bold">
                      {row.min}～{row.max}万円
                    </Typography>
                  </TableCell>
                  <TableCell>
                    <Box sx={{ display: 'flex', alignItems: 'center', gap: 0.5 }}>
                      <LocationOn fontSize="small" color="action" />
                      <Typography variant="body2">{row.location}</Typography>
                    </Box>
                  </TableCell>
                  <TableCell>
                    <Typography variant="body2">{row.rate}/週</Typography>
                  </TableCell>
                  <TableCell>
                    <Typography variant="body2">{row.period}</Typography>
                  </TableCell>
                  <TableCell>
                    <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                      {row.skills.map((skill) => (
                        <Chip
                          key={skill}
                          label={skill}
                          size="small"
                          color="primary"
                          variant="outlined"
                        />
                      ))}
                    </Box>
                  </TableCell>
                  <TableCell>
                    <Chip
                      label={row.flow}
                      size="small"
                      color={getFlowColor(row.flow)}
                      variant="filled"
                    />
                  </TableCell>
                  <TableCell>
                    <Typography variant="body2">{row.interview}回</Typography>
                  </TableCell>
                  <TableCell>
                    <Chip
                      label={row.remote}
                      size="small"
                      color={getRemoteColor(row.remote)}
                      variant="filled"
                    />
                  </TableCell>
                  <TableCell>
                    <Button
                      variant="contained"
                      size="small"
                      sx={{ 
                        background: 'linear-gradient(135deg, #1976d2 0%, #1565c0 100%)',
                        borderRadius: 2
                      }}
                    >
                      メールリンク
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        {/* フッター統計 */}
        <Grid container spacing={3} sx={{ mt: 4 }}>
          <Grid item xs={12} sm={6} md={3}>
            <Card elevation={2} sx={{ borderRadius: 3 }}>
              <CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                  <Avatar sx={{ bgcolor: '#1976d2' }}>
                    <Business />
                  </Avatar>
                  <Box>
                    <Typography variant="body2" color="text.secondary">
                      総案件数
                    </Typography>
                    <Typography variant="h5" fontWeight="bold">
                      {rows.length}
                    </Typography>
                  </Box>
                </Box>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card elevation={2} sx={{ borderRadius: 3 }}>
              <CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                  <Avatar sx={{ bgcolor: '#4caf50' }}>
                    <Computer />
                  </Avatar>
                  <Box>
                    <Typography variant="body2" color="text.secondary">
                      リモート案件
                    </Typography>
                    <Typography variant="h5" fontWeight="bold">
                      {rows.filter(r => r.remote !== "常駐").length}
                    </Typography>
                  </Box>
                </Box>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card elevation={2} sx={{ borderRadius: 3 }}>
              <CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                  <Avatar sx={{ bgcolor: '#9c27b0' }}>
                    <AttachMoney />
                  </Avatar>
                  <Box>
                    <Typography variant="body2" color="text.secondary">
                      平均単価
                    </Typography>
                    <Typography variant="h5" fontWeight="bold">
                      {Math.round(rows.reduce((sum, r) => sum + (r.min + r.max) / 2, 0) / rows.length)}万円
                    </Typography>
                  </Box>
                </Box>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Card elevation={2} sx={{ borderRadius: 3 }}>
              <CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                  <Avatar sx={{ bgcolor: '#ff9800' }}>
                    <People />
                  </Avatar>
                  <Box>
                    <Typography variant="body2" color="text.secondary">
                      元請案件
                    </Typography>
                    <Typography variant="h5" fontWeight="bold">
                      {rows.filter(r => r.flow === "元請").length}
                    </Typography>
                  </Box>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      </Container>
    </div>
  );
}